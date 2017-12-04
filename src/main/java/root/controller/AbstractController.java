package root.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.WindowEvent;

public abstract class AbstractController {

    //Тут нет инъекций спринга
    @FXML
    public void initialize() {
    }

    public abstract void init();

    public abstract EventHandler<WindowEvent> onStart();

}