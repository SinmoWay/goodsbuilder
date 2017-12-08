package root.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import root.db.dto.DictionaryValueDTO;
import root.db.service.DictionaryService;

import javax.annotation.PostConstruct;

public class DictionaryEditController extends AbstractController {

    private DictionaryValueDTO dto = null;

    @FXML
    private TextField valueBox;

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    @PostConstruct
    public void init() {
    }

    @Override
    public EventHandler<WindowEvent> onStart() {
        return event -> {
            setShown(true);
            if (dto != null) {
                valueBox.setText(dto.getNodeText());
            } else {
                valueBox.setText("");
            }
        };
    }

    @Override
    public EventHandler<WindowEvent> onEnd() {
        return event -> {
            setShown(false);
            dto = null;
        };
    }

    @FXML
    public void onSave() {
        if (dto == null) {

        }
        dto.setNodeText(valueBox.getText());
        dictionaryService.saveOrUpdate(dto);
        close();
    }

    @FXML
    public void onCancel() {
        close();
    }

    private void close() {
        ((Stage) valueBox.getScene().getWindow()).hide();
    }

    public void setDTO(DictionaryValueDTO dto) {
        this.dto = dto;
    }

}