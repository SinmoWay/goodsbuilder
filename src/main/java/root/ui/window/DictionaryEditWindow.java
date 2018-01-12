package root.ui.window;

import root.controller.DictionaryEditController;

import java.io.IOException;

public class DictionaryEditWindow extends AbstractWindow<DictionaryEditController> {

    public DictionaryEditWindow() throws IOException {
        super("/window/dictionaryEdit.fxml", "Значение словаря", false);
    }

}