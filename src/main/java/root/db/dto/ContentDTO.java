package root.db.dto;

import root.db.entity.ContentEntity;

public class ContentDTO extends AbstractDTO {

    private String name;
    private Integer amount;

    public ContentDTO(String nullContentText) {
        super(nullContentText, null);
        name = nullContentText;
        amount = null;
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