package root.ui.builder;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlertBuilder {

    public ButtonType alertConfirm(String title, String header, String text) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);

        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(header);
        confirmationDialog.setContentText(text);

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result.orElse(ButtonType.CANCEL);
    }

    public void showError(String header, String content) {
        Alert error = new Alert(Alert.AlertType.ERROR);

        error.setTitle("Ошибка");
        error.setHeaderText(header);
        error.setContentText(content);

        error.showAndWait();
    }

}