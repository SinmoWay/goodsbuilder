package root.controller;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import root.db.service.ContentService;

import javax.annotation.PostConstruct;

public class MainController extends AbstractController {

    @Autowired
    private ContentService contentService;

    @Override
    @PostConstruct
    public void init() {
    }

    @FXML
    public void unloadJson() {
    }

}