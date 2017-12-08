package root.ui.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import root.controller.AbstractController;
import root.db.type.ImgResource;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractWindow<T extends AbstractController> {

    protected final String title;
    protected final String view_path;
    protected final Parent view;
    protected final T controller;

    protected Stage stage;
    protected Scene scene;

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

    public String getViewPath() {
        return view_path;
    }

    public Parent getView() {
        return view;
    }

    public T getController() {
        return controller;
    }

    public Stage getStage() {
        return stage;
    }

    public void startWindow(Stage stage) {
        if (this.stage == null) {
            this.stage = stage;
        }
        if (this.scene == null) {
            this.scene = new Scene(view);
        }
        stage.setScene(scene);
        stage.getIcons().add(new Image(ImgResource.TUX.path()));
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, controller.onStart());
        stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, controller.onEnd());
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

}