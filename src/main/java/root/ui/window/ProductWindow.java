package root.ui.window;

import root.controller.ProductController;

import java.io.IOException;

public class ProductWindow extends AbstractWindow<ProductController> {

    public ProductWindow() throws IOException {
        super("window/productEdit.fxml", "Товар", false);
    }

}