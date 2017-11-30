package root.controller;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import root.db.service.ProductService;

import javax.annotation.PostConstruct;

public class MainController extends AbstractController {

    @Autowired
    private ProductService productService;

    @Override
    @PostConstruct
    public void init() {
    }

    @FXML
    public void unloadJson() {
    }

}