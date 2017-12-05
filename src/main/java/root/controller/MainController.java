package root.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import root.db.dto.AbstractDTO;
import root.db.type.ImgResources;
import root.ui.ImgResource;
import root.ui.TreeBuilder;

import javax.annotation.PostConstruct;

public class MainController extends AbstractController {

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
    private Button refreshButton;
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

    @Autowired
    private TreeBuilder treeBuilder;

    private AbstractDTO currentItem = null;

    @Override
    @PostConstruct
    public void init() {
        this.onMenuHide();

        unloadButton.setGraphic(ImgResource.getSqView(ImgResources.UNLOAD, 25));
        uploadButton.setGraphic(ImgResource.getSqView(ImgResources.UPLOAD, 25));

        addButton.setGraphic(ImgResource.getSqView(ImgResources.ADD, 25));
        //TODO: КАРТИНКА!
        refreshButton.setGraphic(ImgResource.getSqView(ImgResources.TUX, 25));
        editButton.setGraphic(ImgResource.getSqView(ImgResources.EDIT, 25));
        removeButton.setGraphic(ImgResource.getSqView(ImgResources.REMOVE, 25));

        infoText.setText("Успешно загружено");
        ImgResource.initSqView(statusImg, ImgResources.FACE_GOOD, 20);
    }

    @Override
    public EventHandler<WindowEvent> onStart() {
        return event -> onRefresh();
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
    public void onRefresh() {
        TreeItem<AbstractDTO> root = new TreeItem<>(new AbstractDTO("Все"));
        root.setExpanded(true);

        root.getChildren().addAll(treeBuilder.getProductsNode(), treeBuilder.getFabricatorName(), treeBuilder.getContentNames());

        mainTree.setRoot(root);
        mainTree.refresh();
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
        TreeItem<AbstractDTO> item = mainTree.getFocusModel().getFocusedItem();
        currentItem = item == null ? null : item.getValue();
        if(currentItem == null || currentItem.getId() == null) {
            editButton.setDisable(true);
            removeButton.setDisable(true);
        } else {
            editButton.setDisable(false);
            removeButton.setDisable(false);
        }
    }

    @FXML
    public void onTreeRightClicked() {
//        infoText.setText("Права");
//        editButton.setDisable(true);
//        removeButton.setDisable(true);
    }

}