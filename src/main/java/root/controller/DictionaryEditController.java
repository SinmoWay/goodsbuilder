package root.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.annotation.PostConstruct;

public class DictionaryEditController extends AbstractController {

    private String inputText = null;

    @FXML
    private TextField valueBox;

    @Override
    @PostConstruct
    public void init() {
    }

    @Override
    public EventHandler<WindowEvent> onStart() {
        return event -> {
            setShown(true);
            if (valueBox != null) {
                valueBox.setText(inputText);
            } else {
                valueBox.setText("");
            }
        };
    }

    @Override
    public EventHandler<WindowEvent> onEnd() {
        return event -> {
            setShown(false);
            inputText = null;
        };
    }

    @FXML
    public void onSave() {

    }

    @FXML
    public void onCancel() {
        ((Stage) valueBox.getScene().getWindow()).hide();
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

}