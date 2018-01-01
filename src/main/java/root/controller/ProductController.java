package root.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import root.db.dto.ContentDTO;
import root.db.dto.ProductDTO;
import root.db.type.DictionaryType;
import root.db.type.ImgResource;
import root.ui.builder.ImgResourceBuilder;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.stream.Collectors;

public class ProductController extends AbstractController {

    private static final String IMG_CATALOG = "images/";

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

    private ContentDTO currentItem = null;

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

            priceBox.textProperty().addListener(onNumberInputChange(priceBox));
            weightBox.textProperty().addListener(onNumberInputChange(weightBox));

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

    private ChangeListener<String> onNumberInputChange(TextField field) {
        return (observable, oldValue, newValue) -> {
            if (!newValue.matches("^\\d{0,9}([\\.]\\d{0,2})?$")) {
                field.setText(oldValue);
            }
        };
    }

    @FXML
    public void onSelect() {
        TreeItem<ContentDTO> item = table.getFocusModel().getFocusedItem();
        currentItem = item == null ? null : item.getValue();
        if (currentItem == null) {
            addButton.setDisable(true);
            editButton.setDisable(true);
            removeButton.setDisable(true);
        } else {
            addButton.setDisable(false);
            editButton.setDisable(false);
            removeButton.setDisable(false);
        }
    }

    @FXML
    public void onRowAdd() {
    }

    @FXML
    public void onRowEdit() {

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

}