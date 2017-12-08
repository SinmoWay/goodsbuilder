package root.db.dto;

import root.db.entity.ProductEntity;
import root.db.type.ProductType;

import java.util.List;

public class ProductDTO extends AbstractDTO {

    private ProductType type;
    private final List<FabricatorDTO> fabricators;

    public ProductDTO(ProductEntity entity, List<FabricatorDTO> fabricators) {
        super(entity.getId(),
                entity.getDescription()
                + "\n(цена: " + entity.getPrice() + ", вес: " + entity.getWeight() + ", изображение: " + entity.getImage_name() + ")",
                entity.getType());
        this.type = entity.getType();
        this.fabricators = fabricators;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<FabricatorDTO> getFabricators() {
        return fabricators;
    }

}