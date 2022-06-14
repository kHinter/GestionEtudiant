package models;

import java.util.ArrayList;
import java.util.List;

public class Group
{
    private String id;
    private String type;
    private Group parent;
    private List<Group> chilrens;

    public Group()
    {
        this.chilrens = new ArrayList<>();
    }

    public List<Group> getChilrens()
    {
        return this.chilrens;
    }

    public void addChildren(Group group)
    {
        this.chilrens.add(group);
    }

    public Group getParent() {
        return parent;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

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
