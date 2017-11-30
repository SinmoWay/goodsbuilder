package root.db.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "image_name")
    private String image_name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "weight")
    private Double weight;

    @ManyToMany(mappedBy = "")
    private List<FabricatorEntity> fabricators;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<FabricatorEntity> getFabricators() {
        return fabricators;
    }

    public void setFabricators(List<FabricatorEntity> fabricators) {
        this.fabricators = fabricators;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != that.id) return false;
        if (image_name != null ? !image_name.equals(that.image_name) : that.image_name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        return fabricators != null ? fabricators.equals(that.fabricators) : that.fabricators == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (image_name != null ? image_name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (fabricators != null ? fabricators.hashCode() : 0);
        return result;
    }

}