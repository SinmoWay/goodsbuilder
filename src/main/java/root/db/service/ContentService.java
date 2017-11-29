package root.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.db.dao.ContentDao;
import root.db.entity.ContentEntity;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentDao contentDao;

    @Transactional
    public void save(ContentEntity entity) {
        contentDao.saveAndFlush(entity);
    }

    @Transactional
    public List<ContentEntity> getAll() {
        return contentDao.getAll();
    }

}