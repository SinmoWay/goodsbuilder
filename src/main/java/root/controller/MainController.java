package root.controller;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import root.db.entity.ContentEntity;
import root.db.service.ContentService;

import javax.annotation.PostConstruct;
import java.util.List;

public class MainController extends AbstractController {

    @Autowired
    private ContentService contentService;

    @Override
    @PostConstruct
    public void init() {
    }

    @FXML
    public void unloadJson() {
        ContentEntity c = new ContentEntity();
        c.setName("name");
        c.setAmount(1);
        contentService.save(c);

        List<ContentEntity> list = contentService.getAll();
        System.out.println(list);
    }

}