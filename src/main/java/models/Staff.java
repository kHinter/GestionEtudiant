package models;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Staff
{
    private String harpege;
    private String nom;
    private String prenom;
    private String password;
    private List<Role> roles;

    public Staff()
    {
        this.roles = new ArrayList<Role>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles)
    {
        this.roles = roles;
    }

    public void addRole(Role role)
    {
        this.roles.add(role);
    }

    public String getHarpege() {
        return harpege;
    }

    public void setHarpege(String harpege) {
        this.harpege = harpege;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
