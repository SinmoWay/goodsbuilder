package root.db.dao;

import org.springframework.stereotype.Repository;
import root.db.entity.DictionaryValueEntity;
import root.db.type.DictionaryType;

import java.util.List;

@Repository
public class DictionaryValueDAO extends AbstractDAO<DictionaryValueEntity> {

    @Override
    protected Class<DictionaryValueEntity> getClazz() {
        return DictionaryValueEntity.class;
    }

    public List<DictionaryValueEntity> getAllByDictionary(DictionaryType dic) {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("DictionaryValueEntity.getAllByDictionary")
                .setParameter("dic", dic)
                .list();
    }

}