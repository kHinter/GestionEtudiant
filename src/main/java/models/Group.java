package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(type, group.type) && Objects.equals(parent, group.parent) && Objects.equals(chilrens, group.chilrens);
    }

}
