package root.core;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import root.ui.window.MainWindow;

@SpringBootApplication
public class Main extends Application {

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
        main.init(stage);
        main.startWindow();
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