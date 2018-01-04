package root.db.dto;

import root.db.entity.FabricatorEntity;

import java.util.List;

public class FabricatorDTO extends AbstractDTO {

    private final List<ContentDTO> contents;

    public FabricatorDTO(Integer id, String name, List<ContentDTO> contents) {
        super(id, name, null);
        this.contents = contents;
    }

    public FabricatorDTO(FabricatorEntity entity, List<ContentDTO> contents) {
        super(entity.getId(), entity.getName().getValue(), null);
        this.contents = contents;
    }

    public List<ContentDTO> getContents() {
        return contents;
    }

}