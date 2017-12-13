package root.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;
import root.db.dto.AbstractDTO;
import root.db.type.ImgResource;
import root.ui.builder.ImgResourceBuilder;

import javax.annotation.PostConstruct;
import java.io.File;

public class ProductController extends AbstractController {

    private static final String IMG_CATALOG = "images/";

    @FXML
    private ImageView imgPlace;
    @FXML
    private TextField descriptionBox;
    @FXML
    private TextField imgNameBox;
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
            table.setRoot(new TreeItem<>(new AbstractDTO("Состав", null)));
        };
    }

    @Override
    public EventHandler<WindowEvent> onEnd() {
        return event -> {
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

}