package root.db.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.db.dao.ProductDao;
import root.db.dto.ContentDTO;
import root.db.dto.FabricatorDTO;
import root.db.dto.ProductDTO;
import root.db.entity.ProductEntity;
import root.db.type.ProductType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Transactional
    public void save(ProductEntity entity) {
        productDao.save(entity);
    }

    @Transactional
    public List<ProductDTO> getAllInitedByType(ProductType type) {
        return productDao.getAllByType(type)
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

}