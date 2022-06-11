package dao;

import com.example.sae_gestion_etudiants.DatabaseConnection;
import javafx.scene.chart.PieChart;
import models.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO
{
    public List<Role> getAll() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM ROLES;");
        ResultSet queryResults = statement.executeQuery();

        List<Role> roles = new ArrayList<Role>();

        while(queryResults.next())
        {
            Role role = new Role();
            role.setId(queryResults.getInt("Id_role"));
            role.setName(queryResults.getString("Nom_role"));

            roles.add(role);
        }
        return roles;
    }

    public Role getById(int id) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM ROLES WHERE Id_role = ?;");
        statement.setInt(1, id);
        ResultSet queryResults = statement.executeQuery();

        if(queryResults.next())
        {
            Role role = new Role();
            role.setId(queryResults.getInt("Id_role"));
            role.setName(queryResults.getString("Nom_role"));

            return role;
        }
        return null;
    }

    private boolean isIdExists(int id) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT Id_role FROM ROLES WHERE Id_role = ?;");
        statement.setInt(1, id);

        return statement.executeQuery().next();
    }

    public void insert(Role role) throws SQLException {
        if(isIdExists(role.getId())) {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("INSERT INTO ROLES VALUES (?, ?);");
            statement.setInt(1, role.getId());
            statement.setString(2, role.getName());

            statement.executeUpdate();

            System.out.println("Données du rôle insérées avec succès !");
        }
        else
        {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("UPDATE ROLES SET Nom_role = ? WHERE Id_role = ?;");
            statement.setString(1, role.getName());
            statement.setInt(1, role.getId());

            statement.executeUpdate();

            System.out.println("Données du rôle mises à jour avec succès !");
        }
    }

    public void delete(Role role) throws SQLException
    {
        if(isIdExists(role.getId()))
        {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("DELETE FROM ROLES WHERE Id_role = ?;");
            statement.setInt(1, role.getId());

            statement.executeUpdate();
            System.out.println("Données du rôle supprimées avec succès !");
        }
    }
}
