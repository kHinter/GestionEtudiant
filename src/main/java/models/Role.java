package models;

import java.util.ArrayList;

public class Role {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Role)
        {
            Role other = (Role) obj;

            return other.name.equals(this.getName());
        }
        return false;
    }
}
