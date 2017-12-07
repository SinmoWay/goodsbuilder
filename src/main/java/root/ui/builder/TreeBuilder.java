package root.ui.builder;

import javafx.scene.control.TreeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.db.dto.AbstractDTO;
import root.db.service.DictionaryService;
import root.db.service.ProductService;
import root.db.type.DictionaryType;

@Service
public final class TreeBuilder {

    @Autowired
    private ProductService productService;
    @Autowired
    private DictionaryService dictionaryService;

    public TreeItem<AbstractDTO> getProductsNode() {
        TreeItem<AbstractDTO> products = new TreeItem<>(new AbstractDTO("Товары"));
        productService.getAllInited().forEach(product -> {
            TreeItem<AbstractDTO> productItem = new TreeItem<>(product);

            product.getFabricators().forEach(fabricator -> {
                TreeItem<AbstractDTO> fabricatorItem = new TreeItem<>(fabricator);

                fabricator.getContents().forEach(content -> fabricatorItem.getChildren().add(new TreeItem<>(content)));

                productItem.getChildren().add(fabricatorItem);
            });

            products.getChildren().add(productItem);
        });
        return products;
    }

    public TreeItem<AbstractDTO> getContentNames() {
        TreeItem<AbstractDTO> contentNames = new TreeItem<>(new AbstractDTO(DictionaryType.CONTENT_NAME.getDescription()));
        dictionaryService.getAllValuesByDictionary(DictionaryType.CONTENT_NAME).forEach(dicValue -> {
            contentNames.getChildren().add(new TreeItem<>(dicValue));
        });
        return contentNames;
    }

    public TreeItem<AbstractDTO> getFabricatorName() {
        TreeItem<AbstractDTO> fabricatorNames = new TreeItem<>(new AbstractDTO(DictionaryType.FABRICATOR_NAME.getDescription()));
        dictionaryService.getAllValuesByDictionary(DictionaryType.FABRICATOR_NAME).forEach(dicValue -> {
            fabricatorNames.getChildren().add(new TreeItem<>(dicValue));
        });
        return fabricatorNames;
    }

}