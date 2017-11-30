package root.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "dictionary")
public class DictionaryValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dictionary", nullable = false)
    private DictionaryEntity dictionary;

    @Column(name = "value")
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DictionaryEntity getDictionary() {
        return dictionary;
    }

    public void setDictionary(DictionaryEntity dictionary) {
        this.dictionary = dictionary;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DictionaryValueEntity)) return false;

        DictionaryValueEntity that = (DictionaryValueEntity) o;

        if (id != that.id) return false;
        if (dictionary != null ? !dictionary.equals(that.dictionary) : that.dictionary != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (dictionary != null ? dictionary.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

}