package root.ui.builder;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.db.type.ImgResource;

import java.util.Optional;

@Component
public class DialogBuilder {

    @Autowired
    private ImgResourceBuilder imgResourceBuilder;

    public ButtonType alertConfirm(String title, String header, String text) {
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);

        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(header);
        confirmationDialog.setContentText(text);

        setIcon(confirmationDialog);

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result.orElse(ButtonType.CANCEL);
    }

    public void showError(String header, String content) {
        Alert error = new Alert(AlertType.ERROR);

        error.setTitle("Ошибка");
        error.setHeaderText(header);
        error.setContentText(content);

        setIcon(error);

        error.showAndWait();
    }

    public void showInfo(String text, ImgResource img) {
        Alert info = new Alert(AlertType.INFORMATION);

        info.setTitle("Внимание");
        info.setHeaderText(null);
        info.setContentText(text);

        info.setGraphic(imgResourceBuilder.getSqView(img, 50));
        setIcon(info);

        info.showAndWait();
    }

    public void setIcon(Dialog alert) {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(ImgResource.TUX.path()));
    }

}