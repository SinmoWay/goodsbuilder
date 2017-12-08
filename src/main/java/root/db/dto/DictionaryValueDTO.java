package root.db.dto;

import root.db.entity.DictionaryEntity;
import root.db.entity.DictionaryValueEntity;

public class DictionaryValueDTO extends AbstractDTO {

    private DictionaryEntity dictionary;

    public DictionaryValueDTO(DictionaryEntity dictionary) {
        super(null, null);
        this.dictionary = dictionary;
    }

    public DictionaryValueDTO(DictionaryValueEntity entity) {
        super(entity.getId(), entity.getValue(), entity.getDictionary().getName());
        this.dictionary = entity.getDictionary();
    }

    public DictionaryEntity getDictionary() {
        return dictionary;
    }
    
}