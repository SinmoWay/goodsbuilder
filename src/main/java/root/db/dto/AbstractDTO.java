package root.db.dto;

public class AbstractDTO {

    protected final String nodeText;

    public AbstractDTO(String nodeText) {
        this.nodeText = nodeText;
    }

    @Override
    public String toString() {
        return this.nodeText;
    }

}