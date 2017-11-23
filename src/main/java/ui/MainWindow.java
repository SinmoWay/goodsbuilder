package ui;

import controller.MainController;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainWindow extends AbstractWindow<MainController> {

    public MainWindow() throws IOException {
        super("window/main.fxml");
    }

}