package dao;

import com.example.sae_gestion_etudiants.DatabaseConnection;
import models.Role;
import models.Staff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDAO
{
    public Staff getById(String id) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM personnel INNER JOIN assignation ON personnel.Harpege = assignation.Harpege INNER JOIN roles ON roles.Id_role = assignation.Id_role WHERE personnel.Harpege = ?;");
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();

        Staff staff = new Staff();
        if(resultSet.next())
        {
            staff.setHarpege(resultSet.getString("Harpege"));
            staff.setNom(resultSet.getString("Nom_per"));
            staff.setPrenom(resultSet.getString("Prenom_per"));
            staff.setPassword(resultSet.getString("Motdepasse_per"));

            Role role = new Role();
            role.setId(resultSet.getInt("roles.Id_role"));
            role.setName(resultSet.getString("Nom_role"));

            staff.addRole(role);
        }
        else
        {
            return null;
        }
        while(resultSet.next())
        {
            Role role = new Role();
            role.setId(resultSet.getInt("PERSONNEL.Id_role"));
            role.setName(resultSet.getString("Nom_role"));

            staff.addRole(role);
        }
        return staff;
    }
}
