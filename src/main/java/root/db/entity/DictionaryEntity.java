package root.db.entity;

import root.db.type.DictionaryType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "DictionaryEntity.getByName",
                query = "SELECT de FROM DictionaryEntity de WHERE de.name = :dic"
        )
})
@Entity
@Table(name = "DICTIONARY")
public class DictionaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME", nullable = false, unique = true)
    private DictionaryType name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dictionary", cascade = CascadeType.ALL)
    private transient List<DictionaryValueEntity> values = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DictionaryType getName() {
        return name;
    }

    public void setName(DictionaryType name) {
        this.name = name;
    }

    public List<DictionaryValueEntity> getValues() {
        return values;
    }

    public void setValues(List<DictionaryValueEntity> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DictionaryEntity)) return false;

        DictionaryEntity that = (DictionaryEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}