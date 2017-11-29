package root.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import root.controller.AbstractController;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractWindow<T extends AbstractController> {

    protected final String view_path;
    protected final Parent view;
    protected final T controller;

    protected AbstractWindow(String view_path) throws IOException {
        this.view_path = view_path;
        try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream(view_path)) {
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            this.view = loader.getRoot();
            this.controller = loader.getController();
        }
    }

    public String getView_path() {
        return view_path;
    }

    public Parent getView() {
        return view;
    }

    public T getController() {
        return controller;
    }

}