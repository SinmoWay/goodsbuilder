package root.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import root.db.dto.DictionaryValueDTO;
import root.db.service.DictionaryService;

import javax.annotation.PostConstruct;

public class DictionaryEditController extends AbstractController {

    private static final String BEGIN_ADD = "Создание: ";
    private static final String BEGIN_EDIT = "Редактирование: ";

    private DictionaryValueDTO dto = null;

    @FXML
    private Label headerLabel;
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
            if(dto == null) {
                window.closeWindow();
            }
            headerLabel.setText((dto.getId() == null ? BEGIN_ADD : BEGIN_EDIT) + dto.getDictionary().getName().getDescription());
            valueBox.setText(dto.getId() == null ? "" : dto.getNodeText());
        };
    }

    @Override
    public EventHandler<WindowEvent> onEnd() {
        return event -> {
            dto = null;
        };
    }

    @FXML
    public void onSave() {
        if (dto == null) {

        }
        dto.setNodeText(valueBox.getText());
        dictionaryService.saveOrUpdate(dto);
        window.closeWindow();
    }

    @FXML
    public void onCancel() {
        window.closeWindow();
    }

    public void setDTO(DictionaryValueDTO dto) {
        this.dto = dto;
    }

}