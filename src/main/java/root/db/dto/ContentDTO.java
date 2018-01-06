package root.db.dto;

import root.db.entity.ContentEntity;
import root.db.type.NodeType;

public class ContentDTO extends AbstractDTO {

    private String name;
    private Integer amount;

    public ContentDTO(Integer id, String name, Integer amount, NodeType nodeType) {
        super(id, name, nodeType);
        this.name = name;
        this.amount = amount;
    }

    public ContentDTO(ContentEntity entity) {
        super(entity.getId(), entity.getName().getValue() + " - " + String.valueOf(entity.getAmount()), null);
        name = entity.getName().getValue();
        amount = entity.getAmount();
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContentDTO)) return false;

        ContentDTO that = (ContentDTO) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

}