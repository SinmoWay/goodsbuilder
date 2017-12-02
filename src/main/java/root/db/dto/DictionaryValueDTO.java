package root.db.dto;

import root.db.entity.DictionaryValueEntity;

public class DictionaryValueDTO extends AbstractDTO {

    private Integer id;

    public DictionaryValueDTO(DictionaryValueEntity entity) {
        super(entity.getValue());
        id = entity.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DictionaryValueDTO)) return false;

        DictionaryValueDTO that = (DictionaryValueDTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}