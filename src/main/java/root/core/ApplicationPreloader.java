package root.core;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ApplicationPreloader extends Preloader {

    private Scene scene;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
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

        this.scene = new Scene(FXMLLoader.load(getClass().getResource("/window/loading.fxml")));
        this.scene.setFill(null);
        this.scene.getStylesheets().add("/css/loading.css");

    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {

        }
    }
}