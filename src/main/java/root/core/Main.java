package root.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import root.ui.window.MainWindow;

import java.io.InputStream;

@SpringBootApplication
public class Main extends Application {

    private static final Logger log = LogManager.getLogger(Main.class);

    private static String[] savedArgs;
    private ConfigurableApplicationContext context;

    @Autowired
    private MainWindow main;

    public static void main(String[] args) {
        try {
            savedArgs = args;
            launch(Main.class, args);
        } catch (Exception ex) {
            log.error("Ошибка приложения", ex);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Stage loadingStage = initLoading();
            context = SpringApplication.run(getClass(), savedArgs);
            context.getAutowireCapableBeanFactory().autowireBean(this);
            loadingStage.close();
            main.init(stage);
            main.startWindow();
        } catch (Exception ex) {
            log.error("Ошибка запуска", ex);
        }
    }

    @Override
    public void init() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    private Stage initLoading() {
        Stage stage = new Stage();
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
            log.error("Ошибка прелодера", ex);
        }
        return stage;
    }

}