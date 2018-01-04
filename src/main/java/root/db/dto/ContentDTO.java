package root.db.dto;

import root.db.entity.ContentEntity;
import root.db.type.NodeType;

public class ContentDTO extends AbstractDTO {

    private String name;
    private Integer amount;

    public ContentDTO(Integer id, String name, Integer amount, NodeType nodeType) {
        super(name, nodeType);
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

}