package root.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import root.core.JsonConverter;
import root.db.dto.*;
import root.db.service.DictionaryService;
import root.db.service.ProductService;
import root.db.type.DictionaryType;
import root.db.type.ImgResource;
import root.db.type.ProductType;
import root.ui.builder.DialogBuilder;
import root.ui.builder.ImgResourceBuilder;
import root.ui.builder.TreeBuilder;
import root.ui.window.DictionaryEditWindow;
import root.ui.window.ProductEditWindow;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Random;

public class MainController extends AbstractController {

    private static final Random random = new Random();
    private static final String JSON_CATALOG = "json/";

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
    @Autowired
    private ImgResourceBuilder imgResourceBuilder;
    @Autowired
    private DialogBuilder dialogBuilder;
    @Autowired
    private JsonConverter jsonConverter;

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

    private TreeItem<AbstractDTO> currentTreeItem = null;
    private AbstractDTO currentItem = null;

    @Override
    @PostConstruct
    public void init() {
        this.onMenuHide();

        unloadButton.setGraphic(imgResourceBuilder.getSqView(ImgResource.UNLOAD, 25));
        uploadButton.setGraphic(imgResourceBuilder.getSqView(ImgResource.UPLOAD, 25));

        refreshButton.setGraphic(imgResourceBuilder.getSqView(ImgResource.REFRESH, 25));
        addButton.setGraphic(imgResourceBuilder.getSqView(ImgResource.ADD, 25));
        editButton.setGraphic(imgResourceBuilder.getSqView(ImgResource.EDIT, 25));
        removeButton.setGraphic(imgResourceBuilder.getSqView(ImgResource.REMOVE, 25));
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
            window.closeWindow();
        };
    }

    @FXML
    public void onMenuHide() {
        menu.setGraphic(imgResourceBuilder.getSqView(ImgResource.MENU_OPEN, 10));
    }

    @FXML
    public void onMenuShown() {
        menu.setGraphic(imgResourceBuilder.getSqView(ImgResource.MENU_CLOSE, 10));
    }

    @FXML
    public void unloadJson() {
        ButtonType answer = dialogBuilder.alertConfirm("Выгрузка данных", "Действительно выгрузить данные?", "Учтите, что файлы будут заменены!");
        if (answer == ButtonType.OK) {
            for (ProductType type : ProductType.values()) {
                jsonConverter.convertToJsonAndSaveToFile(productService.getAllInitializedByType(type), JSON_CATALOG + type.getFileName());
            }
            dialogBuilder.showInfo("Выгружено", ImgResource.FACE_GOOD);
        }
    }

    @FXML
    public void uploadJson() {
        ButtonType answer = dialogBuilder.alertConfirm("Загрузка данных", "Действительно загрузить данные?", "Учтите, что записи могут быть дублированы!");
        if (answer == ButtonType.OK) {
            for (ProductType type : ProductType.values()) {
                productService.saveOrUpdate(jsonConverter.loadFromFile(JSON_CATALOG + type.getFileName(), type));
            }
            onRefresh();
            dialogBuilder.showInfo("Загружено", ImgResource.FACE_GOOD);
        }
    }

    @FXML
    public void onAdd() {
        normalizeCurrentItem();
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

        TreeItem<AbstractDTO> dict = new TreeItem<>(new AbstractDTO("Словари", null));
        dict.setExpanded(true);

        dict.getChildren().addAll(treeBuilder.getFabricatorName(), treeBuilder.getContentNames());
        root.getChildren().addAll(treeBuilder.getProductsNode(), dict);

        mainTree.setRoot(root);
        mainTree.refresh();
    }

    @FXML
    public void onEdit() throws IOException {
        normalizeCurrentItem();
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
        normalizeCurrentItem();
        if (currentItem == null) {
            setChoseSmtAdvice();
            return;
        }
        if (currentItem.getId() != null) {
            ButtonType answer = dialogBuilder.alertConfirm(
                    "Удалить",
                    "Запись: \"" + currentItem.getNodeText() + "\" будет удалена",
                    "Уверены, что хотите удалить эту запись?"
            );

            if (answer == ButtonType.OK && currentItem instanceof DictionaryValueDTO) {
                if (dictionaryService.canDelete(currentItem.getId(), (DictionaryType) currentItem.getNodeType())) {
                    dictionaryService.delete(currentItem.getId());
                    onRefresh();
                } else {
                    dialogBuilder.showError("Невозможно удалить значение словаря!", "Оно еще используется где-то в товарах");
                }
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

        currentTreeItem = mainTree.getFocusModel().getFocusedItem();
        currentItem = getDtoFromItem(currentTreeItem);
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
        dialogBuilder.showInfo(phrase, random.nextBoolean() ? ImgResource.FACE_NORMAL : ImgResource.FACE_BAD);
    }

    private void setChoseSmtAdvice() {
        dialogBuilder.showInfo("Нужно хоть что-нибудь выбрать", random.nextBoolean() ? ImgResource.FACE_NORMAL : ImgResource.FACE_BAD);
    }

    private AbstractDTO getDtoFromItem(TreeItem<AbstractDTO> item) {
        return item == null ? null : item.getValue();
    }

    private void normalizeCurrentItem() {
        if (currentItem instanceof ContentDTO) {
            currentTreeItem = currentTreeItem.getParent();
            currentItem = getDtoFromItem(currentTreeItem);
        }
        if (currentItem instanceof FabricatorDTO) {
            currentTreeItem = currentTreeItem.getParent();
            currentItem = getDtoFromItem(currentTreeItem);
        }
    }

}