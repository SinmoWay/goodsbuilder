package root.db.dto;

import root.db.type.NodeType;

public class AbstractDTO {

    protected Integer id;
    protected String nodeText;
    protected NodeType nodeType;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodeText() {
        return nodeText;
    }

    public void setNodeText(String nodeText) {
        this.nodeText = nodeText;
    }

    @Override
    public String toString() {
        return this.nodeText;
    }

}