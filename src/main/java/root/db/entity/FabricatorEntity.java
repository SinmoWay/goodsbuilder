package root.db.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FABRICATOR")
public class FabricatorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_NAME", nullable = false)
    private DictionaryValueEntity name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "FABRICATOR_CONTENT",
            joinColumns = {@JoinColumn(name = "ID_FABRICATOR", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ID_CONTENT", nullable = false)})
    private List<ContentEntity> content = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DictionaryValueEntity getName() {
        return name;
    }

    public void setName(DictionaryValueEntity name) {
        this.name = name;
    }

    public List<ContentEntity> getContent() {
        return content;
    }

    public void setContent(List<ContentEntity> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FabricatorEntity)) return false;

        FabricatorEntity that = (FabricatorEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

}