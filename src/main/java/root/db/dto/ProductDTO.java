package root.db.dto;

import root.db.entity.ProductEntity;
import root.db.type.NodeType;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO extends AbstractDTO {

    private String image_name;
    private String description;
    private Double price;
    private Double weight;
    private final List<FabricatorDTO> fabricators;

    public ProductDTO(NodeType type) {
        super(null, type);
        image_name = description = null;
        price = weight = null;
        fabricators = new ArrayList<>();
    }

    public ProductDTO(ProductEntity entity, List<FabricatorDTO> fabricators) {
        super(entity.getId(),
                entity.getDescription()
                        + "\n(цена: " + entity.getPrice() + ", вес: " + entity.getWeight() + ", изображение: " + entity.getImage_name() + ")",
                entity.getType());
        image_name = entity.getImage_name();
        description = entity.getDescription();
        price = entity.getPrice();
        weight = entity.getWeight();
        this.fabricators = fabricators;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public List<FabricatorDTO> getFabricators() {
        return fabricators;
    }

}