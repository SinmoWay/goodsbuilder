package root.core;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ApplicationPreloader extends Preloader {

    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        Stage stage = primaryStage;
        stage.setScene(scene);
        stage.setTitle(null);
        stage.getIcons().clear();
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/window/loading.fxml"));
        this.scene = new Scene(loader.load());
        this.scene.setFill(null);
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {

        }
    }
}