package root.db.dao;

import org.springframework.stereotype.Repository;
import root.db.entity.ProductEntity;
import root.db.type.ProductType;

import java.util.List;

@Repository
public class ProductDAO extends AbstractDAO<ProductEntity> {

    @Override
    protected Class<ProductEntity> getClazz() {
        return ProductEntity.class;
    }

    public List<ProductEntity> getAllByType(ProductType currType) {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("ProductEntity.getAllByType")
                .setParameter("currType", currType)
                .list();
    }

}