package root.ui.window;

import root.controller.LoadingController;

public class LoadingWindow extends AbstractWindow<LoadingController> {

    private static volatile LoadingWindow instance = null;

    public static LoadingWindow getInstance() {
        if (instance == null) {
            synchronized (LoadingWindow.class) {
                if (instance == null) {
                    instance = new LoadingWindow();
                }
            }
        }
        return instance;
    }

    private LoadingWindow() {
        super("/window/loading.fxml", null, false);
    }

}