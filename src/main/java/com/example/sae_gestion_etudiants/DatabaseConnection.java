package com.example.sae_gestion_etudiants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private static final String url = "jdbc:mysql://localhost/but-etudiant";
    private static final String username = "root";
    private static final String password = "";

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex)
        {
            System.out.println("La connexion à la base de données à échouée : " + ex.getMessage());
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed())
        {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
