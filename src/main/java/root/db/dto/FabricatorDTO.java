package root.db.dto;

import root.db.entity.FabricatorEntity;

import java.util.List;

public class FabricatorDTO extends AbstractDTO {

    private String name;
    private final List<ContentDTO> contents;

    public FabricatorDTO(Integer id, String name, List<ContentDTO> contents) {
        super(id, name, null);
        this.name = name;
        this.contents = contents;
    }

    public FabricatorDTO(FabricatorEntity entity, List<ContentDTO> contents) {
        super(entity.getId(), entity.getName().getValue(), null);
        this.name = entity.getName().getValue();
        this.contents = contents;
    }

    public List<ContentDTO> getContents() {
        return contents;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FabricatorDTO)) return false;

        FabricatorDTO that = (FabricatorDTO) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return contents != null ? contents.equals(that.contents) : that.contents == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        return result;
    }

}