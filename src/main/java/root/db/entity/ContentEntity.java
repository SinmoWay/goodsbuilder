package root.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "CONTENT")
public class ContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_NAME", nullable = false)
    private DictionaryValueEntity name;

    @Column(name = "AMOUNT")
    private int amount;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContentEntity)) return false;

        ContentEntity that = (ContentEntity) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + amount;
        return result;
    }

}