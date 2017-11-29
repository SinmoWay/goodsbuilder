package core;

import controller.MainController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.MainWindow;

import java.io.IOException;

@Configuration
public class WindowConfigurations {

    @Bean
    public MainWindow getMainWindow() throws IOException {
        return new MainWindow();
    }

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его произвести все необходимые инъекции.
     */
    @Bean
    public MainController getMainController() throws IOException {
        return getMainWindow().getController();
    }

}