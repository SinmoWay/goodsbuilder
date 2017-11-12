package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ui.MainWindow;

import java.io.IOException;

@Configuration
public class CommonConfigurations {

    @Bean
    public MainWindow getMainWindow() throws IOException {
        return new MainWindow();
    }

}