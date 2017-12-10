package root.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import root.controller.DictionaryEditController;
import root.controller.MainController;
import root.controller.ProductController;
import root.ui.window.DictionaryEditWindow;
import root.ui.window.MainWindow;
import root.ui.window.ProductWindow;

import java.io.IOException;

@Configuration
public class WindowConfigurations {

    @Bean
    public MainWindow getMainWindow() throws IOException {
        return new MainWindow();
    }

    @Bean
    public MainController getMainController() throws IOException {
        return getMainWindow().getController();
    }

    @Bean
    public DictionaryEditWindow getDicEdWindow() throws IOException {
        return new DictionaryEditWindow();
    }
    
    @Bean
    public DictionaryEditController getDicEdController() throws IOException {
        return getDicEdWindow().getController();
    }

    @Bean
    public ProductWindow getProdEdWindow() throws IOException {
        return new ProductWindow();
    }

    @Bean
    public ProductController getProdEdController() throws IOException {
        return getProdEdWindow().getController();
    }

}