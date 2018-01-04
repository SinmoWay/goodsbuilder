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
import root.db.dto.ProductDTO;
import root.db.service.DictionaryService;
import root.db.service.ProductService;
import root.db.type.DictionaryType;
import root.db.type.ImgResource;
import root.db.type.ProductType;
import root.ui.builder.AlertBuilder;
import root.ui.builder.ImgResourceBuilder;
import root.ui.builder.TreeBuilder;
import root.ui.window.DictionaryEditWindow;
import root.ui.window.ProductEditWindow;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Random;

public class MainController extends AbstractController {

    private static final Random random = new Random();

    @Autowired
    private ProductEditWindow prodWindow;
    @Autowired
    private DictionaryEditWindow dicWindow;
    @Autowired
    private TreeBuilder treeBuilder;
    @Autowired
    private DictionaryService dictionaryService;
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
    private Button refreshButton;
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

    private AbstractDTO currentItem = null;

    @Override
    @PostConstruct
    public void init() {
        this.onMenuHide();

        unloadButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.UNLOAD, 25));
        uploadButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.UPLOAD, 25));

        refreshButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.REFRESH, 25));
        addButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.ADD, 25));
        editButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.EDIT, 25));
        removeButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.REMOVE, 25));

        ImgResourceBuilder.initView(statusImg, ImgResource.FACE_GOOD, 20);
        infoText.setText("Успешно загружено");
    }

    @Override
    public EventHandler<WindowEvent> onStart() {
        return event -> {
            dicWindow.init(new Stage());
            prodWindow.init(new Stage());
            dicWindow.getController().setOnEndEvent(endEvent -> onRefresh());
            prodWindow.getController().setOnEndEvent(endEvent -> onRefresh());
            onRefresh();
        };
    }

    @Override
    public EventHandler<WindowEvent> onEnd() {
        return event -> {
            if (dicWindow.isShown()) {
                dicWindow.closeWindow();
            }
            if (prodWindow.isShown()) {
                prodWindow.closeWindow();
            }
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
        if (currentItem == null) {
            setChoseSmtAdvice();
            return;
        }
        if (currentItem.getNodeType() instanceof DictionaryType && !dicWindow.isShown()) {
            dicWindow.getController().setDTO(new DictionaryValueDTO(dictionaryService.getDictionary((DictionaryType) currentItem.getNodeType())));
            dicWindow.startWindow();
        } else if (currentItem.getNodeType() instanceof ProductType && !prodWindow.isShown()) {
            prodWindow.getController().setDTO(new ProductDTO(currentItem.getNodeType()));
            prodWindow.startWindow();
        } else {
            setRandomFaceAndText();
        }
    }

    @FXML
    public void onRefresh() {
        currentItem = null;
        TreeItem<AbstractDTO> root = new TreeItem<>(new AbstractDTO("Все", null));
        root.setExpanded(true);
        root.getChildren().addAll(treeBuilder.getProductsNode(), treeBuilder.getFabricatorName(), treeBuilder.getContentNames());
        mainTree.setRoot(root);
        mainTree.refresh();
    }

    @FXML
    public void onEdit() throws IOException {
        if (currentItem == null) {
            setChoseSmtAdvice();
            return;
        }
        if (currentItem instanceof DictionaryValueDTO && !dicWindow.isShown()) {
            dicWindow.getController().setDTO((DictionaryValueDTO) currentItem);
            dicWindow.startWindow();
        } else if (currentItem instanceof ProductDTO && !prodWindow.isShown()) {
            prodWindow.getController().setDTO((ProductDTO) currentItem);
            prodWindow.startWindow();
        }
    }

    @FXML
    public void onRemove() {
        if (currentItem == null) {
            setChoseSmtAdvice();
            return;
        }
        if (currentItem.getId() != null) {

            ButtonType answer = AlertBuilder.alertConfirm(
                    "Удалить",
                    "Запись: \"" + currentItem.getNodeText() + "\" будет удалена",
                    "Уверены, что хотите удалить эту запись?"
            );

            if (answer == ButtonType.OK && currentItem instanceof DictionaryValueDTO) {
                dictionaryService.delete(currentItem.getId());
                onRefresh();
            } else if (answer == ButtonType.OK && currentItem instanceof ProductDTO) {
                productService.delete(currentItem.getId());
                onRefresh();
            }
        } else {
            setRandomFaceAndText();
        }
    }

    @FXML
    public void onTreeLeftClicked() {
        if (addButton.isDisable()) {
            addButton.setDisable(false);
        }

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

    private void setRandomFaceAndText() {
        String phrase;
        switch (random.nextInt(7)) {
            case 0:
                phrase = "Черти что делаешь";
                break;
            case 1:
                phrase = "И чего ты ожидаешь?";
                break;
            case 2:
                phrase = "Зачем? Как? Почему?";
                break;
            case 3:
                phrase = "Хватит делать фигню";
                break;
            case 4:
                phrase = "Да-да, молодец. Ничего не произойдет";
                break;
            case 5:
                phrase = "Ткни еще раз эту кнопку. Просто так";
                break;
            default:
                phrase = "Бессмысленное действие";
        }
        ImgResourceBuilder.initView(statusImg, random.nextBoolean() ? ImgResource.FACE_NORMAL : ImgResource.FACE_BAD, 20);
        infoText.setText(phrase);
    }

    private void setChoseSmtAdvice() {
        ImgResourceBuilder.initView(statusImg, random.nextBoolean() ? ImgResource.FACE_NORMAL : ImgResource.FACE_BAD, 20);
        infoText.setText("Нужно хоть что-нибудь выбрать");
    }

}