package root.db.dao;

import org.springframework.stereotype.Repository;
import root.db.entity.DictionaryValueEntity;
import root.db.type.DictionaryType;

@Repository
public class DictionaryValueDAO extends AbstractDAO<DictionaryValueEntity> {

    @Override
    protected Class<DictionaryValueEntity> getClazz() {
        return DictionaryValueEntity.class;
    }

    public DictionaryValueEntity getByDictionaryAndValue(DictionaryType dic, String val) {
        return (DictionaryValueEntity) sessionFactory.getCurrentSession()
                .getNamedQuery("DictionaryValueEntity.getByDictionaryAndValue")
                .setParameter("dic", dic)
                .setParameter("val", val)
                .uniqueResult();
    }

}