package root.db.dto;

import root.db.entity.ProductEntity;

import java.util.List;

public class ProductDTO extends AbstractDTO {

    private final List<FabricatorDTO> fabricators;

    public ProductDTO(ProductEntity entity, List<FabricatorDTO> fabricators) {
        super(entity.getId(), entity.getDescription()
                + "\n(цена: " + entity.getPrice() + ", вес: " + entity.getWeight() + ", изображение: " + entity.getImage_name() + ")");
        this.fabricators = fabricators;
    }

    public List<FabricatorDTO> getFabricators() {
        return fabricators;
    }

}