package configuration;

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

    @Bean
    public MainController getMainController() throws IOException {
        return (MainController) getMainWindow().getController();
    }

}