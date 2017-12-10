package root.controller;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class ProductController extends AbstractController {

    @Override
    public void init() {
    }

    @Override
    public EventHandler<WindowEvent> onStart() {
        return event -> {
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

}