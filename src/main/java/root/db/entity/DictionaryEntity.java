package root.db.entity;


import root.db.type.DictionaryType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DICTIONARY")
public class DictionaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME", nullable = false)
    private DictionaryType name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dictionary", cascade = CascadeType.ALL)
    private List<DictionaryValueEntity> values = new ArrayList<>();

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