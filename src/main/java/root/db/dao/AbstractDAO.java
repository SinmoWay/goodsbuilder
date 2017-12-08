package root.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractDAO<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    public T get(Serializable id) {
        return sessionFactory.getCurrentSession().get(getClazz(), id);
    }

    public void save(T object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
    }

    public void save(Collection<T> objects) {
        objects.forEach(o -> sessionFactory.getCurrentSession().saveOrUpdate(o));
    }

    public void refresh(T object) {
        sessionFactory.getCurrentSession().refresh(object);
    }

    public T merge(T object) {
        return (T) sessionFactory.getCurrentSession().merge(object);
    }

    public void delete(Serializable id) {
        T loaded = sessionFactory.getCurrentSession().load(getClazz(), id);
        sessionFactory.getCurrentSession()
                .delete(loaded);
    }

    /**
     * не использовать без явной необходимости
     */
    public void saveAndFlush(T object) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(object);
        currentSession.flush();
        currentSession.clear();
    }

    protected abstract Class<T> getClazz();

}