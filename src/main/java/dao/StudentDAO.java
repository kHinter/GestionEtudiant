package dao;

import com.example.sae_gestion_etudiants.DatabaseConnection;
import models.Student;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getAll() throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM etudiant;");
        ResultSet queryResult = statement.executeQuery();

        List <Student> students = new ArrayList<>();
        while(queryResult.next())
        {
            Student student = new Student();

            student.setNum(queryResult.getString("Num_etu"));
            student.setName(queryResult.getString("Nom_etu"));
            student.setNickname(queryResult.getString("Prenom_etu"));
            student.setAge(queryResult.getInt("Age_etu"));
            student.setBirthdate(queryResult.getDate("Date_etu"));
            student.setDescription(queryResult.getString("Desc_etu"));
            student.setPhotoUrl(queryResult.getString("Photo_etu"));
            student.setHasRepeated(queryResult.getBoolean("Red_etu"));
            student.setHasResign(queryResult.getBoolean("Dem_etu"));
            student.setPassword(queryResult.getString("Motdepasse_etu"));
            student.setRegistrationYear(queryResult.getInt("AnneeInscription_etu"));

            GroupDAO groupDAO = new GroupDAO();
            student.setTDGroup(groupDAO.getById(queryResult.getString("Id_groupe_TD")));
            student.setTPGroup(groupDAO.getById(queryResult.getString("Id_groupe_TP")));

            PromotionDAO promoDAO = new PromotionDAO();
            student.setPromotion(promoDAO.getById(queryResult.getInt("Id_promo")));

            //On ajoute l'étudiant à la liste
            students.add(student);
        }

        return students;
    }
    public Student getById(String id) throws SQLException
    {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM etudiant WHERE Num_etu = ?;");
        statement.setString(1,  id);
        ResultSet queryResult = statement.executeQuery();

        if(queryResult.next())
        {
            Student student = new Student();

            student.setNum(queryResult.getString("Num_etu"));
            student.setName(queryResult.getString("Nom_etu"));
            student.setNickname(queryResult.getString("Prenom_etu"));
            student.setAge(queryResult.getInt("Age_etu"));
            student.setBirthdate(queryResult.getDate("Date_etu"));
            student.setDescription(queryResult.getString("Desc_etu"));
            student.setPhotoUrl(queryResult.getString("Photo_etu"));
            student.setHasRepeated(queryResult.getBoolean("Red_etu"));
            student.setHasResign(queryResult.getBoolean("Dem_etu"));
            student.setPassword(queryResult.getString("Motdepasse_etu"));
            student.setRegistrationYear(queryResult.getInt("AnneeInscription_etu"));

            GroupDAO groupDAO = new GroupDAO();
            student.setTDGroup(groupDAO.getById(queryResult.getString("Id_groupe_TD")));
            student.setTPGroup(groupDAO.getById(queryResult.getString("Id_groupe_TP")));

            PromotionDAO promoDAO = new PromotionDAO();
            student.setPromotion(promoDAO.getById(queryResult.getInt("Id_promo")));

            return student;
        }
        return null;
    }

    public void insert(Student student) throws SQLException
    {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("INSERT INTO ETUDIANT VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        statement.setString(1, student.getNum());
        statement.setString(2, student.getName());
        statement.setString(3, student.getNickname());
        statement.setInt(4, student.getAge());
        statement.setDate(5, student.getBirthdate());
        statement.setString(6, student.getDescription());
        statement.setString(7, student.getPhotoUrl());
        statement.setBoolean(8, student.isHasRepeated());
        statement.setBoolean(9, student.isHasResign());
        statement.setString(10, student.getPassword());
        statement.setInt(11, student.getRegistrationYear());
        statement.setString(12, student.getTDGroup().getId());
        statement.setString(13, student.getTPGroup().getId());
        statement.setInt(14, student.getPromotion().getId());

        statement.executeUpdate();
        System.out.println("Données de l'étudiant insérées avec succès !");
    }

    public void update(Student student) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("UPDATE ETUDIANT SET Nom_etu = ?, Prenom_etu = ?, Age_etu = ?, Date_etu = ?, Desc_etu = ?,  Photo_etu = ?, Red_etu = ?, Dem_etu = ?, Motdepasse_etu = ?, AnneeInscription_etu = ?, Id_groupe_TD = ?, Id_groupe_TP = ?, Id_promo = ? WHERE Num_etu = ?;");
        statement.setString(1, student.getName());
        statement.setString(2, student.getNickname());
        statement.setInt(3, student.getAge());
        statement.setDate(4, student.getBirthdate());
        statement.setString(5, student.getDescription());
        statement.setString(6, student.getPhotoUrl());
        statement.setBoolean(7, student.isHasRepeated());
        statement.setBoolean(8, student.isHasResign());
        statement.setString(9, student.getPassword());
        statement.setInt(10, student.getRegistrationYear());
        statement.setString(11, student.getTDGroup().getId());
        statement.setString(12, student.getTPGroup().getId());
        statement.setInt(13, student.getPromotion().getId());
        statement.setString(14, student.getNum());

        statement.executeUpdate();
        System.out.println("Données de l'étudiant mises à jour avec succès !");
    }

    public void delete(Student student) throws SQLException {
        PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("DELETE FROM ETUDIANT WHERE Num_etu = ?;");
        statement.setString(1, student.getNum());
        
        statement.executeUpdate();
        System.out.println("Données de l'étudiant supprimées avec succès !");
    }
}