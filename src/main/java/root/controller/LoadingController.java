package root.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.WindowEvent;

public class LoadingController extends AbstractController {

    @FXML
    private ImageView loadingImg;

    @FXML
    public void initialize() {
        loadingImg.setPreserveRatio(true);
        loadingImg.setSmooth(true);
        loadingImg.setCache(true);
    }

    @Override
    public void init() {
    }

    @Override
    public EventHandler<WindowEvent> onStart() {
        return Event::consume;
    }

    @Override
    public EventHandler<WindowEvent> onEnd() {
        return Event::consume;
    }

}