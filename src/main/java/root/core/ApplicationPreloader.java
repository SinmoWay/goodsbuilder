package root.core;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;

public class ApplicationPreloader extends Preloader {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        initLoading();
    }

    private void initLoading() {
        try (InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("window/loading.fxml")) {
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);

            stage.setScene(new Scene(loader.getRoot()));

            stage.setTitle(null);
            stage.getIcons().clear();

            stage.centerOnScreen();
            stage.setResizable(false);

            stage.initStyle(StageStyle.UNDECORATED);


            stage.show();
        } catch (Exception ex) {
            System.out.println("Ошибка прелодера");
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.close();
            System.out.println("Приложение загружено и запускается ...");
        }
    }
}
