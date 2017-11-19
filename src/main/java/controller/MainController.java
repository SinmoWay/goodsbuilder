package controller;

import db.dao.ContentDao;
import db.entity.ContentEntity;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MainController extends AbstractController {

    @Autowired
    private ContentDao contentDao;

    @Override
    @PostConstruct
    public void init() {
    }

    @FXML
    public void unloadJson() {
        ContentEntity c = new ContentEntity();
        c.setName("name");
        c.setAmount(1);
        contentDao.save(c);
    }

}