package root.core;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import root.ui.window.MainWindow;

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
        } catch (Throwable t) {
            log.error("Ошибка приложения", t);
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            main.init(stage);
            main.startWindow();
        } catch (Throwable t) {
            log.error("Ошибка запуска", t);
        }
    }

    @Override
    public void init() {
        context = SpringApplication.run(getClass(), savedArgs);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() {
        context.close();
    }

}