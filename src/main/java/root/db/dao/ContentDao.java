package root.db.dao;

import org.springframework.stereotype.Repository;
import root.db.entity.ContentEntity;

import java.util.List;

@Repository
public class ContentDao extends AbstractDAO<ContentEntity> {

    @Override
    protected Class<ContentEntity> getClazz() {
        return ContentEntity.class;
    }

    public List<ContentEntity> getAll() {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("ContentEntity.getAll")
                .list();
    }

}