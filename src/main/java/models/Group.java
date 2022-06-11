package models;

public class Group
{
    private String id;
    private String type;

    public Group getParent() {
        return parent;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    private Group parent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
