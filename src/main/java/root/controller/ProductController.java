package root.controller;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import root.db.dto.AbstractDTO;
import root.db.dto.ProductDTO;
import root.db.type.ImgResource;
import root.ui.builder.ImgResourceBuilder;

import javax.annotation.PostConstruct;
import java.io.File;

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
    private TextField weightBox;
    @FXML
    private TreeTableView<AbstractDTO> table;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;

    @Override
    @PostConstruct
    public void init() {
        addButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.ADD, 25));
        editButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.EDIT, 25));
        removeButton.setGraphic(ImgResourceBuilder.getSqView(ImgResource.REMOVE, 25));
        ImgResourceBuilder.initView(imgPlace, ImgResource.EYE);
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

            TreeItem<AbstractDTO> root = new TreeItem<>(new AbstractDTO("Состав", null));

            if (dto.getId() != null) {
                descriptionBox.setText(dto.getDescription());
                imgNameBox.setText(dto.getImage_name());
                priceBox.setText(String.valueOf(dto.getPrice()));
                weightBox.setText(String.valueOf(dto.getWeight()));
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