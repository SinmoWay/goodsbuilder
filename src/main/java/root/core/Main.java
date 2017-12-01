package root.core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import root.db.type.ImgResources;
import root.ui.MainWindow;

@SpringBootApplication
public class Main extends Application {

    @Value("${ui.title:Welcome}")
    private String windowTitle;

    private static String[] savedArgs;
    private ConfigurableApplicationContext context;

    @Autowired
    private MainWindow main;

    public static void main(String[] args) {
        savedArgs = args;
        launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(main.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(ImgResources.TUX.path()));
        stage.show();
    }

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(getClass(), savedArgs);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

}