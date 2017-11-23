import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ui.MainWindow;

@SpringBootApplication
public class Main extends Application {

    @Value("${ui.title:Welcome}")
    private String windowTitle;
    @Autowired
    private MainWindow main;

    private static String[] savedArgs;
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Main.savedArgs = args;
        launch(Main.class, args);
    }

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(Main.class, savedArgs);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(main.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

}