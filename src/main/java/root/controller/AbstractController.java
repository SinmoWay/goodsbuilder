package root.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.WindowEvent;
import root.ui.window.AbstractWindow;

public abstract class AbstractController {

    protected AbstractWindow window;
    protected EventHandler<WindowEvent> onEndEvent;

    //Тут нет инъекций спринга
    @FXML
    public void initialize() {
    }

    public <T extends AbstractWindow> void setThisWindow(T window) {
        this.window = window;
    }

    public void setOnEndEvent(EventHandler<WindowEvent> onEndEvent) {
        this.onEndEvent = onEndEvent;
    }

    public abstract void init();

    public abstract EventHandler<WindowEvent> onStart();

    public abstract EventHandler<WindowEvent> onEnd();

}