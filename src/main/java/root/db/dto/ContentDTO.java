package root.db.dto;

import root.db.entity.ContentEntity;

public class ContentDTO extends AbstractDTO {

    public ContentDTO(ContentEntity entity) {
        super(entity.getId(), entity.getName().getValue() + " - " + String.valueOf(entity.getAmount()), null);
    }

}