package root.controller;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import root.db.dto.ContentDTO;
import root.db.dto.DictionaryValueDTO;
import root.db.dto.ProductDTO;
import root.db.service.DictionaryService;
import root.db.type.DictionaryType;
import root.db.type.ImgResource;
import root.ui.builder.ImgResourceBuilder;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductController extends AbstractController {

    private static final String IMG_CATALOG = "images/";
    private static final String REG_DOUBLE = "^\\d{0,9}([\\.]\\d{0,2})?$";
    private static final String REG_INT = "^\\d{0,9}$";

    private ProductDTO dto = null;

    @FXML
    private ImageView imgPlace;
    @FXML
    private TextField descriptionBox;
    @FXML
    private TextField imgNameBox;
    @FXML
    private TextField priceBox;
    @FXML
    private ImageView rubImgView;
    @FXML
    private TextField weightBox;
    @FXML
    private TreeTableView<ContentDTO> table;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;

    @Autowired
    private DictionaryService dictionaryService;

    private TreeItem<ContentDTO> currentItem = null;

    @Override
    @PostConstruct
    public void init() {
        addButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.ADD, 25));
        editButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.EDIT, 25));
        removeButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.REMOVE, 25));
        ImgResourceBuilder.initView(imgPlace, ImgResource.EYE);
        ImgResourceBuilder.initView(rubImgView, ImgResource.RUB, 20);
    }

    @Override
    public EventHandler<WindowEvent> onStart() {
        return event -> {
            if (dto == null) {
                window.closeWindow();
                return;
            }

            priceBox.textProperty().addListener(onNumberInputChange(priceBox, true));
            weightBox.textProperty().addListener(onNumberInputChange(weightBox, true));

            TreeTableColumn<ContentDTO, String> contentNameColumn = new TreeTableColumn<>("Название продукта");
            contentNameColumn.setCellValueFactory(
                    p -> new ReadOnlyStringWrapper(p.getValue().getValue().getName())
            );
            TreeTableColumn<ContentDTO, String> amountColumn = new TreeTableColumn<>("Количество");
            amountColumn.setCellValueFactory(p -> {
                Integer amount = p.getValue().getValue().getAmount();
                return new ReadOnlyStringWrapper(amount == null ? "" : String.valueOf(amount));
            });
            table.getColumns().addAll(contentNameColumn, amountColumn);

            TreeItem<ContentDTO> root = new TreeItem<>(new ContentDTO("Состав", null, null));
            root.getChildren().clear();

            if (dto.getId() != null) {
                descriptionBox.setText(dto.getDescription());
                imgNameBox.setText(dto.getImage_name());
                priceBox.setText(String.valueOf(dto.getPrice()));
                weightBox.setText(String.valueOf(dto.getWeight()));

                root.getChildren().addAll(
                        dto.getFabricators().stream()
                                .map(fabricator -> {
                                    TreeItem<ContentDTO> currFabricator = new TreeItem<>(new ContentDTO(fabricator.getNodeText(), null, DictionaryType.FABRICATOR_NAME));
                                    currFabricator.getChildren().addAll(
                                            fabricator.getContents().stream()
                                                    .map(content -> new ContentDTO(content.getName(), content.getAmount(), DictionaryType.CONTENT_NAME))
                                                    .map(TreeItem::new)
                                                    .collect(Collectors.toList())
                                    );
                                    return currFabricator;
                                })
                                .collect(Collectors.toList())
                );
            }

            table.setRoot(root);
        };
    }

    @Override
    public EventHandler<WindowEvent> onEnd() {
        return event -> {
            dto = null;
            if (onEndEvent != null) {
                onEndEvent.handle(event);
            }
        };
    }

    @FXML
    public void onImgShow() {
        try {
            String imgUri = new File(IMG_CATALOG + imgNameBox.getText().trim()).toURI().toString();
            ImgResourceBuilder.initView(imgPlace, imgUri);
        } catch (Exception ex) {
            Alert error = new Alert(Alert.AlertType.ERROR);

            error.setTitle("Ошибка");
            error.setHeaderText("Отобразить изображение невозможно!");
            error.setContentText("Проверьте расположение и указанное имя файла. Не забудьте указать расширение.");

            error.showAndWait();
        }
    }

    private ChangeListener<String> onNumberInputChange(TextField field, boolean isDouble) {
        return (observable, oldValue, newValue) -> {
            if (!newValue.matches(isDouble ? REG_DOUBLE : REG_INT)) {
                field.setText(oldValue);
            }
        };
    }

    @FXML
    public void onSelect() {
        TreeItem<ContentDTO> item = table.getFocusModel().getFocusedItem();
        currentItem = item == null ? null : item;
        if (isNotRoot()) {
            editButton.setDisable(false);
            removeButton.setDisable(false);
        } else {
            editButton.setDisable(true);
            removeButton.setDisable(true);
        }
        addButton.setDisable(false);
    }

    @FXML
    public void onRowAdd() {
        ContentDTO newOne = createEditDialog(false, isNotRoot());
        if (newOne != null) {
            getParent().getChildren().add(new TreeItem<>(newOne));
        }
    }

    @FXML
    public void onRowEdit() {
        if (isNotRoot()) {
            ContentDTO newOne = createEditDialog(true, currentItem.getValue().getNodeType() == DictionaryType.CONTENT_NAME);
            if (newOne != null) {
                currentItem.setValue(newOne);
            }
        }
    }

    @FXML
    public void onRowRemove() {

    }

    @FXML
    public void onSave() {

    }

    @FXML
    public void onCancel() {
        window.closeWindow();
    }

    public void setDTO(ProductDTO dto) {
        this.dto = dto;
    }

    private TreeItem<ContentDTO> getParent() {
        if (currentItem == null || currentItem.getValue().getNodeType() == null) {
            return table.getRoot();
        }
        if (currentItem.getValue().getNodeType() == DictionaryType.CONTENT_NAME) {
            return currentItem.getParent();
        }
        return currentItem;
    }

    private boolean isNotRoot() {
        return currentItem != null && currentItem.getValue().getNodeType() != null;
    }

    private ContentDTO createEditDialog(boolean isEdit, boolean isContent) {
        Dialog<ContentDTO> dialog = new Dialog<>();

        String text = isEdit ? "Редактирование" : "Добавление";
        dialog.setTitle(text);

        text += isContent ? " состава" : " производителя" + "\nЗаполните все поля";
        dialog.setHeaderText(text);

        dialog.setGraphic(ImgResourceBuilder.getSqView(ImgResource.TUX, 50));

        ButtonType saveButtonType = new ButtonType("Сохранить", ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Отменить", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(cancelButtonType, saveButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<String> name = new ComboBox<>();
        TextField amount = new TextField();

        name.setItems(new ObservableListWrapper<>(
                dictionaryService
                        .getAllValuesByDictionaryType(isContent ? DictionaryType.CONTENT_NAME : DictionaryType.FABRICATOR_NAME)
                        .stream()
                        .map(DictionaryValueDTO::getNodeText)
                        .collect(Collectors.toList())
        ));
        if (isEdit) {
            name.getSelectionModel().select(currentItem.getValue().getName());
            amount.setText(String.valueOf(currentItem.getValue().getAmount()));
        } else {
            name.getSelectionModel().selectFirst();
            amount.setText("");
        }
        grid.add(new Label("Название:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Количество:"), 0, 1);
        grid.add(amount, 1, 1);

        amount.textProperty().addListener(onNumberInputChange(amount, false));

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(name::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                String nameValue = name.getValue();
                if (nameValue == null || nameValue.isEmpty()) {
                    return null;
                }

                String amountText = amount.getText();
                Integer amountValue = amountText == null || amountText.isEmpty() ? null : Integer.valueOf(amountText.trim());

                return new ContentDTO(
                        nameValue,
                        amountValue,
                        isContent ? DictionaryType.CONTENT_NAME : DictionaryType.FABRICATOR_NAME
                );
            }
            return null;
        });

        Optional<ContentDTO> result = dialog.showAndWait();
        return result.orElse(null);
    }

}