package root.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "DICTIONARY_VALUE")
public class DictionaryValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_DICTIONARY", nullable = false)
    private DictionaryEntity dictionary;

    @Column(name = "VALUE")
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dictionary != null ? !dictionary.equals(that.dictionary) : that.dictionary != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dictionary != null ? dictionary.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

}