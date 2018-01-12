package root.core;

import javafx.application.Preloader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import root.ui.window.LoadingWindow;

public class ApplicationPreloader extends Preloader {

    @Override
    public void start(Stage primaryStage) {
        LoadingWindow loading = LoadingWindow.getInstance();
        loading.init(primaryStage);

        loading.getScene().setFill(null);

        loading.getStage().getIcons().clear();
        loading.getStage().initStyle(StageStyle.UNDECORATED);
        loading.getStage().setAlwaysOnTop(true);

        loading.startWindow();
    }

    @Override
    public void init() throws Exception {
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
    }

}