package root.db.entity;

import root.db.type.ProductType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "ProductEntity.getAllByType",
                query = "SELECT pe FROM ProductEntity pe WHERE pe.type = :currType ORDER BY pe.id"
        )
})
@Entity
@Table(name = "PRODUCT")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "IMAGE_NAME")
    private String image_name;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private ProductType type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "WEIGHT")
    private Double weight;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "PRODUCT_FABRICATOR",
            joinColumns = {@JoinColumn(name = "ID_PRODUCT", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ID_FABRICATOR", nullable = false)})
    private List<FabricatorEntity> fabricators = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (image_name != null ? !image_name.equals(that.image_name) : that.image_name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        return fabricators != null ? fabricators.equals(that.fabricators) : that.fabricators == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (image_name != null ? image_name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (fabricators != null ? fabricators.hashCode() : 0);
        return result;
    }

}