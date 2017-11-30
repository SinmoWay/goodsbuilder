package root.db.dao;

import org.springframework.stereotype.Repository;
import root.db.entity.ProductEntity;

@Repository
public class ProductDao extends AbstractDAO<ProductEntity> {

    @Override
    protected Class<ProductEntity> getClazz() {
        return ProductEntity.class;
    }

}