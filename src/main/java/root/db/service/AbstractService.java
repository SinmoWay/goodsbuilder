package root.db.service;

import org.springframework.transaction.annotation.Transactional;
import root.db.dao.AbstractDAO;

import java.io.Serializable;

public abstract class AbstractService<T extends AbstractDAO> {

    protected T dao;

    protected AbstractService(T dao) {
        this.dao = dao;
    }

    @Transactional
    public void delete(Serializable id) {
        dao.delete(id);
    }

}