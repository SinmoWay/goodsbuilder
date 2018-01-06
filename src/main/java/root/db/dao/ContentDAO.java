package root.db.dao;

import org.springframework.stereotype.Repository;
import root.db.entity.ContentEntity;

@Repository
public class ContentDAO extends AbstractDAO<ContentEntity> {

    @Override
    protected Class<ContentEntity> getClazz() {
        return ContentEntity.class;
    }

}