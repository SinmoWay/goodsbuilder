package root.db.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.db.dao.*;
import root.db.dto.ContentDTO;
import root.db.dto.FabricatorDTO;
import root.db.dto.ProductDTO;
import root.db.entity.ContentEntity;
import root.db.entity.DictionaryValueEntity;
import root.db.entity.FabricatorEntity;
import root.db.entity.ProductEntity;
import root.db.type.DictionaryType;
import root.db.type.ProductType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService extends AbstractService<ProductDAO> {

    private final FabricatorDAO fabricatorDAO;
    private final ContentDAO contentDAO;

    private final DictionaryDAO dictionaryDAO;
    private final DictionaryValueDAO dictionaryValueDAO;

    @Autowired
    public ProductService(ProductDAO dao, FabricatorDAO fabricatorDAO, ContentDAO contentDAO, DictionaryDAO dictionaryDAO, DictionaryValueDAO dictionaryValueDAO) {
        super(dao);
        this.fabricatorDAO = fabricatorDAO;
        this.contentDAO = contentDAO;
        this.dictionaryDAO = dictionaryDAO;
        this.dictionaryValueDAO = dictionaryValueDAO;
    }

    @Transactional
    public void save(ProductEntity entity) {
        dao.save(entity);
    }

    @Transactional
    public List<ProductDTO> getAllInitializedByType(ProductType type) {
        return dao.getAllByType(type)
                .stream()
                .filter(Objects::nonNull)
                .map(product -> {

                    Hibernate.initialize(product.getFabricators());

                    List<FabricatorDTO> fabricators = product.getFabricators()
                            .stream()
                            .filter(Objects::nonNull)
                            .map(fabricator -> {

                                Hibernate.initialize(fabricator.getContent());

                                List<ContentDTO> contents = fabricator.getContent()
                                        .stream()
                                        .filter(Objects::nonNull)
                                        .map(ContentDTO::new)
                                        .collect(Collectors.toList());

                                return new FabricatorDTO(fabricator, contents);
                            })
                            .collect(Collectors.toList());

                    return new ProductDTO(product, fabricators);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveOrUpdate(List<ProductDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return;
        }
        dtos.forEach(this::saveOrUpdate);
    }

    @Transactional
    public void saveOrUpdate(ProductDTO dto) {
        ProductEntity entity;

        if (dto.getId() == null) {
            entity = new ProductEntity();
        } else {
            entity = dao.get(dto.getId());
        }

        entity.setDescription(dto.getDescription());
        entity.setImage_name(dto.getImage_name());
        entity.setType((ProductType) dto.getNodeType());
        entity.setPrice(dto.getPrice());
        entity.setWeight(dto.getWeight());

        entity.getFabricators().clear();
        entity.getFabricators().addAll(dto.getFabricators().stream()
                .map(this::convertFabricatorFromDTO)
                .collect(Collectors.toList())
        );

        dao.save(entity);
    }

    private FabricatorEntity convertFabricatorFromDTO(FabricatorDTO fabricatorDTO) {
        FabricatorEntity fabricatorEntity;

        if (fabricatorDTO.getId() == null) {
            fabricatorEntity = new FabricatorEntity();
        } else {
            fabricatorEntity = fabricatorDAO.get(fabricatorDTO.getId());
        }

        fabricatorEntity.setId(fabricatorDTO.getId());
        fabricatorEntity.setName(getDicValueByDicTypeAndValue(DictionaryType.FABRICATOR_NAME, fabricatorDTO.getName()));

        fabricatorEntity.setContent(
                fabricatorDTO.getContents().stream()
                        .map(this::convertContentFromDTO)
                        .collect(Collectors.toList())
        );

        return fabricatorEntity;
    }

    private ContentEntity convertContentFromDTO(ContentDTO contentDTO) {
        ContentEntity contentEntity;

        if (contentDTO.getId() == null) {
            contentEntity = new ContentEntity();
        } else {
            contentEntity = contentDAO.get(contentDTO.getId());
        }

        contentEntity.setId(contentDTO.getId());
        contentEntity.setName(getDicValueByDicTypeAndValue(DictionaryType.CONTENT_NAME, contentDTO.getName()));
        contentEntity.setAmount(contentDTO.getAmount());
        return contentEntity;
    }

    private DictionaryValueEntity getDicValueByDicTypeAndValue(DictionaryType type, String value) {
        DictionaryValueEntity contentName = dictionaryValueDAO.getByDictionaryAndValue(type, value);
        if (contentName != null) {
            return contentName;
        }
        contentName = new DictionaryValueEntity();
        contentName.setDictionary(dictionaryDAO.getByName(type));
        contentName.setValue(value);
        dictionaryValueDAO.save(contentName);
        return contentName;
    }

}