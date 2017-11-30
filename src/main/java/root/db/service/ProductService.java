package root.db.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.db.dao.ProductDao;
import root.db.entity.ProductEntity;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Transactional
    public void save(ProductEntity entity) {
        productDao.save(entity);
    }

    @Transactional
    public List<ProductEntity> getAllInited() {
        List<ProductEntity> products =  productDao.getAll();
        products.forEach(product -> {
            Hibernate.initialize(product.getFabricators());
            product.getFabricators().forEach(fabricator -> Hibernate.initialize(fabricator.getContent()));
        });
        return products;
    }

}