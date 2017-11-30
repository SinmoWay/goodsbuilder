package root.db.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fabricator")
public class FabricatorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "id_name")
    private DictionaryValueEntity name;

    @ManyToMany(mappedBy = "")
    private List<ContentEntity> content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

}