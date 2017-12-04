package root.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import root.controller.AbstractController;
import root.db.type.ImgResources;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractWindow<T extends AbstractController> {

    protected final String title;
    protected final String view_path;
    protected final Parent view;
    protected final T controller;

    protected AbstractWindow(String view_path, String title) throws IOException {
        this.title = title;
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

    public void startWindow(Stage stage) {
        stage.setTitle(title);
        stage.setScene(new Scene(view));
        stage.getIcons().add(new Image(ImgResources.TUX.path()));
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, controller.onStart());
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

}