package db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractDAO {

    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void delete(Class type, Serializable id){
        Object loaded = sessionFactory.getCurrentSession().load(type, id);
        sessionFactory.getCurrentSession()
                .delete(loaded);
    }

    public <T> void save(T object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
    }

    public <T> void save(Collection<T> objects) {
        objects.forEach(o -> sessionFactory.getCurrentSession().saveOrUpdate(o));
    }

    public <T> void refresh(T object) {
        sessionFactory.getCurrentSession().refresh(object);
    }

    public <T> T merge(T object) {
        return (T) sessionFactory.getCurrentSession().merge(object);
    }

    /**
     * не использовать без явной необходимости
     */
    public <T> void saveAndFlush(T object) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(object);
        currentSession.flush();
        currentSession.clear();
    }

}