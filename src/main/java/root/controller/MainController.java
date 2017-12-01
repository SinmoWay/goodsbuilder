package root.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import root.controller.util.ImgResource;
import root.db.dto.AbstractDTO;
import root.db.service.ProductService;
import root.db.type.DictionaryType;
import root.db.type.ImgResources;

import javax.annotation.PostConstruct;

public class MainController extends AbstractController {

    @Autowired
    private ProductService productService;

    //MENU
    @FXML
    private Menu menu;
    @FXML
    private MenuItem unloadButton;
    @FXML
    private MenuItem uploadButton;

    //TOOLBAR
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;

    //BODY
    @FXML
    private TreeView<AbstractDTO> mainTree;

    //STATUS PART
    @FXML
    private Label infoText;
    @FXML
    private ImageView statusImg;

    @Override
    @PostConstruct
    public void init() {
        this.onMenuHide();

        unloadButton.setGraphic(ImgResource.getSqView(ImgResources.UNLOAD, 25));
        uploadButton.setGraphic(ImgResource.getSqView(ImgResources.UPLOAD, 25));

        addButton.setGraphic(ImgResource.getSqView(ImgResources.ADD, 25));
        editButton.setGraphic(ImgResource.getSqView(ImgResources.EDIT, 25));
        removeButton.setGraphic(ImgResource.getSqView(ImgResources.REMOVE, 25));

        infoText.setText("Успешно загружено");
        ImgResource.initSqView(statusImg, ImgResources.FACE_GOOD, 20);

        TreeItem<AbstractDTO> root = new TreeItem<>(new AbstractDTO("Все"));
        root.setExpanded(true);

        TreeItem<AbstractDTO> products = new TreeItem<>(new AbstractDTO("Товары"));
        //TODO: Заполнение товаров

        TreeItem<AbstractDTO> contentNames = new TreeItem<>(new AbstractDTO(DictionaryType.CONTENT_NAME.getDescription()));
        //TODO: Заполнение имен контента

        TreeItem<AbstractDTO> fabricatorNames = new TreeItem<>(new AbstractDTO(DictionaryType.FABRICATOR_NAME.getDescription()));
        //TODO: Заполнение имен производителей

        root.getChildren().addAll(products, contentNames, fabricatorNames);

        mainTree.setRoot(root);
    }

    @FXML
    public void onMenuHide() {
        menu.setGraphic(ImgResource.getSqView(ImgResources.MENU_OPEN, 10));
    }

    @FXML
    public void onMenuShown() {
        menu.setGraphic(ImgResource.getSqView(ImgResources.MENU_CLOSE, 10));
    }

    @FXML
    public void unloadJson() {
        infoText.setText("Выгружаем");
    }

    @FXML
    public void uploadJson() {
        infoText.setText("Загружаем");
    }

    @FXML
    public void onAdd() {
        infoText.setText("Добавляем");
    }

    @FXML
    public void onEdit() {
        infoText.setText("Едет");
    }

    @FXML
    public void onRemove() {
        infoText.setText("Ремува");
    }

    @FXML
    public void onTreeLeftClicked() {
        infoText.setText("Лева");
        editButton.setDisable(false);
        removeButton.setDisable(false);
    }

    @FXML
    public void onTreeRightClicked() {
        infoText.setText("Права");
        editButton.setDisable(true);
        removeButton.setDisable(true);
    }

}