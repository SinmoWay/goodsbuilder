package root.db.entity;


import root.db.type.Dictionary;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dictionary")
public class DictionaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private Dictionary name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_dictionary")
    private List<DictionaryValueEntity> values = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Dictionary getName() {
        return name;
    }

    public void setName(Dictionary name) {
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

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}