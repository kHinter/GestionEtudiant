package dao;

import com.example.sae_gestion_etudiants.DatabaseConnection;
import javafx.scene.chart.PieChart;
import models.Group;

import java.sql.*;
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
                group.getParent().addChildren(group);
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
            }

            this.addChildren(group);

            promotionsList.add(group);
        }
        return promotionsList;
    }

    public List<Group> getAllTD() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM GROUPE WHERE Type_groupe = 'TD';");

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
            }
            promotionsList.add(group);
        }
        return promotionsList;
    }

    private void addChildren(Group parent) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM GROUPE WHERE Parente_groupe = ?;");
        statement.setString(1, parent.getId());

        ResultSet queryResults = statement.executeQuery();
        while(queryResults.next())
        {
            Group child = new Group();
            child.setId(queryResults.getString("Id_groupe"));
            child.setType(queryResults.getString("Type_groupe"));
            child.setParent(parent);

            parent.addChildren(child);
            this.addChildren(child);
        }
    }

    public List<Group> getAllTP() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM GROUPE WHERE Type_groupe = 'TP';");

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
            }
            promotionsList.add(group);
        }
        return promotionsList;
    }

    public boolean isIdExists(String id) throws SQLException {
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

            if(group.getParent() == null)
            {
                statement.setNull(3, Types.NULL);
            }
            else
            {
                statement.setString(3, group.getParent().getId());
            }

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
