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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO)) return false;

        ProductDTO that = (ProductDTO) o;

        if (image_name != null ? !image_name.equals(that.image_name) : that.image_name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        return fabricators != null ? fabricators.equals(that.fabricators) : that.fabricators == null;
    }

    @Override
    public int hashCode() {
        int result = image_name != null ? image_name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (fabricators != null ? fabricators.hashCode() : 0);
        return result;
    }

}