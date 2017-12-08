package root.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.WindowEvent;

public abstract class AbstractController {

    private boolean shown = false;

    //Тут нет инъекций спринга
    @FXML
    public void initialize() {
    }

    public abstract void init();

    public abstract EventHandler<WindowEvent> onStart();

    public abstract EventHandler<WindowEvent> onEnd();

    public boolean isShown() {
        return shown;
    }

    protected void setShown(boolean shown) {
        this.shown = shown;
    }

}