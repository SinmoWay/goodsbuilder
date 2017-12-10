package root.db.dao;

import org.springframework.stereotype.Repository;
import root.db.entity.DictionaryEntity;
import root.db.type.DictionaryType;

@Repository
public class DictionaryDAO extends AbstractDAO<DictionaryEntity> {

    @Override
    protected Class<DictionaryEntity> getClazz() {
        return DictionaryEntity.class;
    }

    public DictionaryEntity getByName(DictionaryType dic) {
        return (DictionaryEntity) sessionFactory.getCurrentSession()
                .getNamedQuery("DictionaryEntity.getByName")
                .setParameter("dic", dic)
                .uniqueResult();
    }

}