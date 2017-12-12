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
    protected final String viewPath;
    protected final boolean resizable;
    protected final Parent view;
    protected final T controller;

    private Stage stage;
    private Scene scene;

    private boolean shown = false;

    protected AbstractWindow(String viewPath, String title, boolean resizable) throws IOException {
        this.title = title;
        this.viewPath = viewPath;
        this.resizable = resizable;
        try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream(viewPath)) {
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            this.view = loader.getRoot();
            this.controller = loader.getController();
            this.controller.setThisWindow(this);
        }
    }

    public T getController() {
        return controller;
    }

    public boolean isShown() {
        return shown;
    }

    public void startWindow(Stage newStage) {
        if (this.stage == null) {
            this.stage = newStage;
        }
        if (this.scene == null) {
            this.scene = new Scene(view);
        }
        this.stage.setTitle(title);
        this.stage.setScene(scene);

        this.stage.getIcons().add(new Image(ImgResource.TUX.path()));

        this.stage.addEventHandler(WindowEvent.WINDOW_SHOWING, controller.onStart());
        this.stage.addEventHandler(WindowEvent.WINDOW_HIDING, controller.onEnd());

        this.stage.centerOnScreen();
        this.stage.setResizable(resizable);

        this.shown = true;
        this.stage.show();
    }

    public void closeWindow() {
        if (shown) {
            this.shown = false;
            this.stage.hide();
        }
    }

}