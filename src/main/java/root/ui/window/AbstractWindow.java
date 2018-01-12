package root.ui.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import root.controller.AbstractController;
import root.db.type.ImgResource;
import root.ui.builder.DialogBuilder;

import java.io.IOException;

public abstract class AbstractWindow<T extends AbstractController> {

    @Autowired
    protected DialogBuilder dialogBuilder;

    private final String title;
    private final boolean resizable;
    private Parent view;
    protected final T controller;

    private Stage stage;

    private boolean shown = false;

    AbstractWindow(String viewPath, String title, boolean resizable) throws IOException {
        this.title = title;
        this.resizable = resizable;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewPath));

        this.view = loader.load();
        this.controller = loader.getController();
        this.controller.setThisWindow(this);
    }

    public T getController() {
        return controller;
    }

    public boolean isShown() {
        return shown;
    }

    public void init(Stage stage) {
        this.stage = stage;

        Scene scene = new Scene(view);
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
            dialogBuilder.showError(
                    "Ужасная ошибка разработчика",
                    "Сообщите об этом разработчику, описав условия появления"
            );
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