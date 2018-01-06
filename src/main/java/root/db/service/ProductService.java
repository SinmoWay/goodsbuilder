package root.db.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.db.dao.ContentDAO;
import root.db.dao.DictionaryValueDAO;
import root.db.dao.FabricatorDAO;
import root.db.dao.ProductDAO;
import root.db.dto.ContentDTO;
import root.db.dto.FabricatorDTO;
import root.db.dto.ProductDTO;
import root.db.entity.ContentEntity;
import root.db.entity.FabricatorEntity;
import root.db.entity.ProductEntity;
import root.db.type.DictionaryType;
import root.db.type.ProductType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService extends AbstractService<ProductDAO> {

    private final DictionaryValueDAO dictionaryValueDAO;
    private final FabricatorDAO fabricatorDAO;
    private final ContentDAO contentDAO;

    @Autowired
    public ProductService(ProductDAO dao, DictionaryValueDAO dictionaryValueDAO, FabricatorDAO fabricatorDAO, ContentDAO contentDAO) {
        super(dao);
        this.dictionaryValueDAO = dictionaryValueDAO;
        this.fabricatorDAO = fabricatorDAO;
        this.contentDAO = contentDAO;
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
                .map(fabricatorDTO -> {
                    FabricatorEntity fabricatorEntity;

                    if (fabricatorDTO.getId() == null) {
                        fabricatorEntity = new FabricatorEntity();
                    } else {
                        fabricatorEntity = fabricatorDAO.get(fabricatorDTO.getId());
                    }

                    fabricatorEntity.setId(fabricatorDTO.getId());
                    fabricatorEntity.setName(dictionaryValueDAO.getByDictionaryAndValue(DictionaryType.FABRICATOR_NAME, fabricatorDTO.getNodeText()));

                    fabricatorEntity.setContent(
                            fabricatorDTO.getContents().stream()
                                    .map(contentDTO -> {
                                        ContentEntity contentEntity;

                                        if (contentDTO.getId() == null) {
                                            contentEntity = new ContentEntity();
                                        } else {
                                            contentEntity = contentDAO.get(contentDTO.getId());
                                        }

                                        contentEntity.setId(contentDTO.getId());
                                        contentEntity.setName(dictionaryValueDAO.getByDictionaryAndValue(DictionaryType.CONTENT_NAME, contentDTO.getName()));
                                        contentEntity.setAmount(contentDTO.getAmount());
                                        return contentEntity;
                                    })
                                    .collect(Collectors.toList())
                    );

                    return fabricatorEntity;
                })
                .collect(Collectors.toList())
        );

        dao.save(entity);
    }

}