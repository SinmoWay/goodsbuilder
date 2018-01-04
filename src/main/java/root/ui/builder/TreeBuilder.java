package root.ui.builder;

import javafx.scene.control.TreeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.db.dto.AbstractDTO;
import root.db.service.DictionaryService;
import root.db.service.ProductService;
import root.db.type.DictionaryType;
import root.db.type.ProductType;

@Component
public final class TreeBuilder {

    @Autowired
    private ProductService productService;
    @Autowired
    private DictionaryService dictionaryService;

    public TreeItem<AbstractDTO> getProductsNode() {
        TreeItem<AbstractDTO> products = new TreeItem<>(new AbstractDTO("Товары", null));
        products.setExpanded(true);

        for (ProductType type : ProductType.values()) {
            TreeItem<AbstractDTO> typedProduct = new TreeItem<>(new AbstractDTO(type.getDescription(), type));

            productService.getAllInitedByType(type).forEach(product -> {
                TreeItem<AbstractDTO> productItem = new TreeItem<>(product);

                product.getFabricators().forEach(fabricator -> {
                    TreeItem<AbstractDTO> fabricatorItem = new TreeItem<>(fabricator);

                    fabricator.getContents().forEach(content -> fabricatorItem.getChildren().add(new TreeItem<>(content)));

                    productItem.getChildren().add(fabricatorItem);
                });

                typedProduct.getChildren().add(productItem);
            });

            products.getChildren().add(typedProduct);
        }
        return products;
    }

    public TreeItem<AbstractDTO> getContentNames() {
        TreeItem<AbstractDTO> contentNames = new TreeItem<>(new AbstractDTO(DictionaryType.CONTENT_NAME.getDescription(), DictionaryType.CONTENT_NAME));
        dictionaryService.getAllValuesByDictionaryType(DictionaryType.CONTENT_NAME).forEach(dicValue -> contentNames.getChildren().add(new TreeItem<>(dicValue)));
        return contentNames;
    }

    public TreeItem<AbstractDTO> getFabricatorName() {
        TreeItem<AbstractDTO> fabricatorNames = new TreeItem<>(new AbstractDTO(DictionaryType.FABRICATOR_NAME.getDescription(), DictionaryType.FABRICATOR_NAME));
        dictionaryService.getAllValuesByDictionaryType(DictionaryType.FABRICATOR_NAME).forEach(dicValue -> fabricatorNames.getChildren().add(new TreeItem<>(dicValue)));
        return fabricatorNames;
    }

}