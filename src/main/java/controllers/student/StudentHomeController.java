package controllers.student;

import controllers.student.StudentController;
import dao.StudentDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.text.Text;
import models.Student;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class StudentHomeController extends StudentController implements Initializable {

    @FXML
    private Text studentNameText;

    @FXML
    private Text groupNameText;

    @FXML
    private ChoiceBox groupChoiceBox;

    @FXML
    private TableView studentsTableView;

    @FXML
    private TableColumn nicknameTableColumn;

    @FXML
    private TableColumn nameTableColumn;

    @FXML
    private TableColumn ageTableColumn;

    @FXML
    private TableColumn birthdateTableColumn;

    @FXML
    private TableColumn promotionTableColumn;

    @FXML
    private TableColumn TDGroupTableColumn;

    @FXML
    private TableColumn TPGroupTableColumn;

    @FXML
    private TableColumn descriptionTableColumn;

    @Override
    public void init() {
        Student student = getConnectedStudent();
        studentNameText.setText(student.getNickname() + " " + student.getName().toUpperCase());

        groupChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observableValue, String oldValue, String newValue)
            {
                List<Student> studentList = null;
                if(newValue.equals("Promo"))
                {
                    String promotionName = getConnectedStudent().getPromotion().getId();
                    groupNameText.setText(promotionName);

                    StudentDAO dao = new StudentDAO();
                    try
                    {
                        studentList = dao.getByGroupId(promotionName);

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

                studentsTableView.getItems().clear();
                for(Student student : studentList)
                {
                    Map<String, Object> item = new HashMap<>();
                    item.put("nickname", student.getNickname());
                    item.put("name", student.getName());
                    item.put("age", student.getAge());
                    item.put("birthdate", student.getBirthdate());
                    item.put("promotion", student.getPromotion().getId());
                    item.put("TD", "TD" + student.getTDGroup().getId());
                    item.put("TP", "TP" + student.getTPGroup().getId());
                    item.put("description", student.getDescription());

                    studentsTableView.getItems().add(item);
                }
            }
        });

        groupChoiceBox.getSelectionModel().select(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        groupChoiceBox.getItems().addAll("Promo","TD","TP");

        nicknameTableColumn.setCellValueFactory(new MapValueFactory<>("nickname"));
        nameTableColumn.setCellValueFactory(new MapValueFactory<>("name"));
        ageTableColumn.setCellValueFactory(new MapValueFactory<>("age"));
        birthdateTableColumn.setCellValueFactory(new MapValueFactory<>("birthdate"));
        promotionTableColumn.setCellValueFactory(new MapValueFactory<>("promotion"));
        TDGroupTableColumn.setCellValueFactory(new MapValueFactory<>("TD"));
        TPGroupTableColumn.setCellValueFactory(new MapValueFactory<>("TP"));
        descriptionTableColumn.setCellValueFactory(new MapValueFactory<>("description"));
    }
}
