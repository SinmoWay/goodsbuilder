package configuration;

import controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.MainWindow;

import java.io.IOException;

@Configuration
public class WindowConfigurations {

    @Autowired
    private MainWindow mainWindow;

    @Bean
    public MainController getMainController() throws IOException {
        return mainWindow.getController();
    }

}