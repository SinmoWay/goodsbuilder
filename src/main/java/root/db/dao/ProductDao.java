package root.db.dao;

import org.springframework.stereotype.Repository;
import root.db.entity.ProductEntity;

import java.util.List;

@Repository
public class ProductDao extends AbstractDAO<ProductEntity> {

    @Override
    protected Class<ProductEntity> getClazz() {
        return ProductEntity.class;
    }

    public List<ProductEntity> getAll() {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("ProductEntity.getAll")
                .list();
    }

}