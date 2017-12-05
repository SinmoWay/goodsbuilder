package root.db.dto;

import root.db.entity.DictionaryValueEntity;

public class DictionaryValueDTO extends AbstractDTO {

    public DictionaryValueDTO(DictionaryValueEntity entity) {
        super(entity.getId(), entity.getValue());
    }

}