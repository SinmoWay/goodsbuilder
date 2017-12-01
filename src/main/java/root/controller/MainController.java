package root.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import org.springframework.beans.factory.annotation.Autowired;
import root.db.service.ProductService;

import javax.annotation.PostConstruct;

public class MainController extends AbstractController {

    @Autowired
    private ProductService productService;

    @FXML
    private TreeView<String> mainTree;
    @FXML
    private Label infoText;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;

    @Override
    @PostConstruct
    public void init() {
        infoText.setText("Успешно загружено");
    }

    @FXML
    public void unloadJson() {
    }

    @FXML
    public void uploadJson() {
    }

    @FXML
    public void onAdd() {
    }

    @FXML
    public void onEdit() {
    }

    @FXML
    public void onRemove() {
    }

}