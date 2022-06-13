package controllers;

import com.example.sae_gestion_etudiants.MainApplication;
import dao.StudentDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Student;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StudentTrombinoscopeController extends StudentController implements Initializable
{

    @FXML
    private Text studentNameText;

    @FXML
    private Text groupNameText;

    @FXML
    private ChoiceBox groupChoiceBox;

    @FXML
    private FlowPane trombinoscopeFlowPane;

    @Override
    public void init()
    {
        Student connectedStudent = getConnectedStudent();
        studentNameText.setText(connectedStudent.getNickname() + " " + connectedStudent.getName().toUpperCase());

        groupChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observableValue, String oldValue, String newValue)
            {
                List<Student> studentList = null;
                if(newValue.equals("Promo"))
                {
                    String promotionName = getConnectedStudent().getPromotion().getName();
                    groupNameText.setText(promotionName);

                    StudentDAO dao = new StudentDAO();
                    try
                    {
                        studentList = dao.getByPromotionName(promotionName);

                    } catch (SQLException e)
                    {
                        System.out.println("Erreur lors de la requête :" + e.getMessage());
                    }
                }
                else if(newValue.equals("TD"))
                {
                    String TDGroupName = getConnectedStudent().getTDGroup().getId();
                    groupNameText.setText("TD" + TDGroupName);

                    StudentDAO dao = new StudentDAO();
                    try
                    {
                        studentList = dao.getByGroupId(TDGroupName);

                    } catch (SQLException e)
                    {
                        System.out.println("Erreur lors de la requête :" + e.getMessage());
                    }
                }
                else
                {
                    String TPGroupName = getConnectedStudent().getTPGroup().getId();
                    groupNameText.setText("TP" + TPGroupName);

                    StudentDAO dao = new StudentDAO();
                    try
                    {
                        studentList = dao.getByGroupId(TPGroupName);

                    } catch (SQLException e)
                    {
                        System.out.println("Erreur lors de la requête :" + e.getMessage());
                    }
                }

                for(Student student : studentList)
                {
                    VBox vbox = new VBox();
                    vbox.setPrefWidth(130);
                    vbox.setPrefHeight(160);
                    vbox.setAlignment(Pos.TOP_CENTER);
                    vbox.setSpacing(10);

                    ImageView imageView = new ImageView(new Image(MainApplication.class.getResource("Images/person.png").toExternalForm()));

                    vbox.getChildren().add(imageView);
                    Text text = new Text();

                    vbox.getChildren().add(new Text(student.getNickname() + " " + student.getName().toUpperCase()));
                    trombinoscopeFlowPane.getChildren().add(vbox);
                }
            }
        });

        groupChoiceBox.getSelectionModel().select(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        groupChoiceBox.getItems().addAll("Promo", "TD", "TP");
    }
}
