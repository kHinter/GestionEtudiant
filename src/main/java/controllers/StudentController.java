package controllers;

import com.example.sae_gestion_etudiants.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Student;

import java.io.IOException;

public abstract class StudentController {
    private Student connectedStudent;

    public void setConnectedStudent(Student student)
    {
        this.connectedStudent = student;
    }

    public Student getConnectedStudent()
    {
        return this.connectedStudent;
    }

    public abstract void init();

    protected abstract void onTrombinoscopeClicked();
}
