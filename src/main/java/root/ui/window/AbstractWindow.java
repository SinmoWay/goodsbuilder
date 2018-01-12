package root.ui.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import root.controller.AbstractController;
import root.db.type.ImgResource;

import java.io.IOException;

public abstract class AbstractWindow<T extends AbstractController> {

    private static final Logger log = LogManager.getLogger(AbstractWindow.class);

    private final String title;
    private final boolean resizable;
    protected final T controller;

    private Parent view;
    private Scene scene;
    private Stage stage;
    private boolean shown = false;

    protected AbstractWindow(String viewPath, String title, boolean resizable) {
        this.title = title;
        this.resizable = resizable;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewPath));

        try {
            this.view = loader.load();
        } catch (IOException e) {
            log.error("Ошибка загрузки VIEW", e);
        }

        this.controller = loader.getController();
        this.controller.setThisWindow(this);
    }

    public T getController() {
        return controller;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }

    public boolean isShown() {
        return shown;
    }

    public void init(Stage stage) {
        if (stage == null) {
            log.error("NULL stage!");
            return;
        }
        this.stage = stage;

        this.scene = new Scene(view);
        this.stage.setScene(scene);

        this.stage.setTitle(title);
        this.stage.getIcons().add(new Image(ImgResource.TUX.path()));

        this.stage.addEventHandler(WindowEvent.WINDOW_SHOWING, controller.onStart());
        this.stage.addEventHandler(WindowEvent.WINDOW_HIDING, controller.onEnd());

        this.stage.centerOnScreen();
        this.stage.setResizable(resizable);
    }

    public void startWindow() {
        if (this.stage == null) {
            log.error("NULL stage!");
            return;
        }
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