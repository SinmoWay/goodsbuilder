package root.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class LoadingController {

    @FXML
    private ImageView loadingImg;

    @FXML
    public void initialize() {
        loadingImg.setPreserveRatio(true);
        loadingImg.setSmooth(true);
        loadingImg.setCache(true);
    }
}