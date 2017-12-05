package root.db.dto;

public class AbstractDTO {

    protected final Integer id;
    protected final String nodeText;

    public AbstractDTO(String nodeText) {
        id = null;
        this.nodeText = nodeText;
    }

    protected AbstractDTO(Integer id, String nodeText) {
        this.id = id;
        this.nodeText = nodeText;
    }

    public Integer getId() {
        return id;
    }

    public String getNodeText() {
        return nodeText;
    }

    @Override
    public String toString() {
        return this.nodeText;
    }

}