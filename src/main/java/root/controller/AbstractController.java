package root.controller;

import javafx.fxml.FXML;

public abstract class AbstractController {

    //Тут нет инъекций спринга
    @FXML
    public void initialize() {
    }

    public abstract void init();

}