package controller;

import db.dao.ContentDao;
import db.entity.ContentEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MainController extends AbstractController {

    @Autowired
    private ContentDao contentDao;

    @Override
    @PostConstruct
    public void init() {
        ContentEntity c = new ContentEntity();
        c.setId(1);
        c.setName("name");
        c.setAmount(1);
        contentDao.save(c);
    }

}