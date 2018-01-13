package root.core;

import javafx.animation.FadeTransition;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class ApplicationPreloader extends Preloader {

    Scene scene;
    Stage stage;

    public ApplicationPreloader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/window/loading.fxml"));
        try {
            this.scene = new Scene(loader.load());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        this.scene.setFill(null);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {

            FadeTransition ft = new FadeTransition(Duration.seconds(2), scene.getRoot());
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1);

            ft.play();

            ft.setOnFinished(event -> stage.close());

        }
    }
}