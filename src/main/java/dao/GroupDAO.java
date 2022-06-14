package dao;

import com.example.sae_gestion_etudiants.DatabaseConnection;
import javafx.scene.chart.PieChart;
import models.Group;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO
{
    public Group getById(String id) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM GROUPE WHERE Id_groupe = ?;");
        statement.setString(1, id);

        ResultSet queryResults = statement.executeQuery();
        Group group = new Group();

        if(queryResults.next())
        {
            group.setId(queryResults.getString("Id_groupe"));
            group.setType(queryResults.getString("Type_groupe"));

            String parenteGroupe = queryResults.getString("Parente_groupe");
            if(parenteGroupe != null)
            {
                group.setParent(getById(parenteGroupe));
            }
            return group;
        }
        return null;
    }

    public List<Group> getAllPromotions() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM GROUPE WHERE Type_groupe = 'PROMOTION';");

        ResultSet queryResults = statement.executeQuery();
        List<Group> promotionsList = new ArrayList<>();

        while(queryResults.next())
        {
            Group group = new Group();
            group.setId(queryResults.getString("Id_groupe"));
            group.setType(queryResults.getString("Type_groupe"));

            String parenteGroup = queryResults.getString("Parente_groupe");
            if(parenteGroup != null)
            {
                group.setParent(getById(parenteGroup));
                group.getParent().addChildren(group);
            }
            promotionsList.add(group);
        }
        return promotionsList;
    }

    private boolean isIdExists(String id) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT Id_groupe FROM GROUPE WHERE Id_groupe = ?;");
        statement.setString(1, id);

        return statement.executeQuery().next();
    }

    public void save(Group group) throws SQLException {
        //Si l'ID n'existe pas dans la base de données, on peut insérer les données
        if(!isIdExists(group.getId()))
        {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("INSERT INTO GROUPE VALUES (?, ?, ?);");
            statement.setString(1, group.getId());
            statement.setString(2, group.getType());
            statement.setString(3, group.getParent().getId());

            statement.executeUpdate();
            System.out.println("Données du groupe insérées avec succès !");
        }
        else
        {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("UPDATE GROUPE SET Type_groupe = ?, Parente_groupe = ? WHERE Id_groupe = ?;");
            statement.setString(1, group.getType());
            statement.setString(2, group.getParent().getId());
            statement.setString(3, group.getId());

            statement.executeUpdate();
            System.out.println("Données du groupe mises à jour avec succès !");
        }
    }

    public void delete(Group group) throws SQLException {
        if(isIdExists(group.getId()))
        {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("DELETE FROM GROUPE WHERE Id_groupe = ?;");
            statement.setString(1, group.getId());

            statement.executeUpdate();
            System.out.println("Données du groupe supprimées avec succès !");
        }
    }
}
