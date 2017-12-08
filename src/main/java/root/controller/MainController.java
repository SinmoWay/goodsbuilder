package root.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import root.db.dto.AbstractDTO;
import root.db.dto.DictionaryValueDTO;
import root.db.type.ImgResource;
import root.ui.builder.ImgResourceBuilder;
import root.ui.builder.TreeBuilder;
import root.ui.window.DictionaryEditWindow;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class MainController extends AbstractController {

    @Autowired
    private DictionaryEditWindow dicValue;

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

        unloadButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.UNLOAD, 25));
        uploadButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.UPLOAD, 25));

        addButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.ADD, 25));
        refreshButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.REFRESH, 25));
        editButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.EDIT, 25));
        removeButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.REMOVE, 25));

        infoText.setText("Успешно загружено");
        ImgResourceBuilder.initSqView(statusImg, ImgResource.FACE_GOOD, 20);
    }

    @Override
    public EventHandler<WindowEvent> onStart() {
        return event -> {
            setShown(true);
            onRefresh();
        };
    }

    @Override
    public EventHandler<WindowEvent> onEnd() {
        return event -> {
            dicValue.getStage().close();
            setShown(false);
        };
    }

    @FXML
    public void onMenuHide() {
        menu.setGraphic(ImgResourceBuilder.getSqView(ImgResource.MENU_OPEN, 10));
    }

    @FXML
    public void onMenuShown() {
        menu.setGraphic(ImgResourceBuilder.getSqView(ImgResource.MENU_CLOSE, 10));
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
        if (!dicValue.getController().isShown()) {
//            dicValue.getController().setDTO(new DictionaryValueDTO(currentItem.));
            dicValue.startWindow(new Stage());
        }
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
    public void onEdit() throws IOException {
        if (!dicValue.getController().isShown()) {
            if (currentItem instanceof DictionaryValueDTO) {
                dicValue.getController().setDTO((DictionaryValueDTO) currentItem);
                dicValue.startWindow(new Stage());
            }
        }
    }

    @FXML
    public void onRemove() {
        infoText.setText("Ремува");
    }

    @FXML
    public void onTreeLeftClicked() {
        TreeItem<AbstractDTO> item = mainTree.getFocusModel().getFocusedItem();
        currentItem = item == null ? null : item.getValue();
        if (currentItem == null || currentItem.getId() == null) {
            editButton.setDisable(true);
            removeButton.setDisable(true);
        } else {
            editButton.setDisable(false);
            removeButton.setDisable(false);
        }
    }

    @FXML
    public void onTreeRightClicked() {
    }

}