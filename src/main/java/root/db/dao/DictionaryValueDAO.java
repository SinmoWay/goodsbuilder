package root.db.dao;

import org.springframework.stereotype.Repository;
import root.db.entity.DictionaryValueEntity;

@Repository
public class DictionaryValueDAO extends AbstractDAO<DictionaryValueEntity> {

    @Override
    protected Class<DictionaryValueEntity> getClazz() {
        return DictionaryValueEntity.class;
    }

}