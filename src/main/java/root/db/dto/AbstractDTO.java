package root.db.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import root.db.type.NodeType;

public class AbstractDTO {

    @JsonIgnore
    protected Integer id;
    @JsonIgnore
    protected String nodeText;
    @JsonIgnore
    protected NodeType nodeType;

    public AbstractDTO(String nodeText, NodeType nodeType) {
        id = null;
        this.nodeText = nodeText;
        this.nodeType = nodeType;
    }

    protected AbstractDTO(Integer id, String nodeText, NodeType nodeType) {
        this.id = id;
        this.nodeText = nodeText;
        this.nodeType = nodeType;
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

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return this.nodeText;
    }

}