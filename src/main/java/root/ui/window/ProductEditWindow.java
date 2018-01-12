package root.ui.window;

import root.controller.ProductController;

import java.io.IOException;

public class ProductEditWindow extends AbstractWindow<ProductController> {

    public ProductEditWindow() throws IOException {
        super("/window/productEdit.fxml", "Товар", false);
    }

}