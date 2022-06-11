package dao;

import com.example.sae_gestion_etudiants.DatabaseConnection;
import models.Group;
import models.Promotion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PromotionDAO
{
    public Promotion getById(int id) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM PROMOTION WHERE Id_promo = ?;");
        statement.setInt(1, id);
        ResultSet queryResults = statement.executeQuery();

        if(queryResults.next())
        {
            Promotion promotion = new Promotion();
            promotion.setId(queryResults.getInt("Id_promo"));
            promotion.setName(queryResults.getString("Nom_promo"));

            return promotion;
        }
        return null;
    }

    private boolean isIdExists(int id) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT Id_promo FROM PROMOTION WHERE Id_promo = ?;");
        statement.setInt(1, id);

        return statement.executeQuery().next();
    }

    public void save(Promotion promotion) throws SQLException
    {
        if(!isIdExists(promotion.getId()))
        {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("INSERT INTO PROMOTION VALUES (?, ?);");
            statement.setInt(1, promotion.getId());
            statement.setString(2, promotion.getName());

            statement.executeUpdate();
            System.out.println("Données de la promotion insérées avec succès !");
        }
        else
        {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("UPDATE PROMOTION SET Nom_promo = ? WHERE Id_promo = ?;");
            statement.setString(1, promotion.getName());
            statement.setInt(2, promotion.getId());

            statement.executeUpdate();
            System.out.println("Données de la promotion mises à jour avec succès !");
        }
    }

    public void delete(Promotion promotion) throws SQLException {
        if(isIdExists(promotion.getId()))
        {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("DELETE FROM PROMOTION WHERE Id_promo = ?;");
            statement.setInt(1, promotion.getId());

            statement.executeUpdate();
            System.out.println("Données de la promotion supprimées avec succès !");
        }
    }
}
