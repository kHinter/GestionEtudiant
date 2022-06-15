package controllers.teacher;

import javafx.scene.input.MouseEvent;
import models.Staff;

public abstract class TeacherController {

    private Staff connectedTeacher;

    public void setConnectedStaff(Staff teacher)
    {
        this.connectedTeacher = teacher;
    }

    public Staff getConnectedStaff()
    {
        return this.connectedTeacher;
    }

    public abstract void init();

    public void onDeconnectionClicked(MouseEvent mouseEvent)
    {

    }

    public void onInfoPersoClicked(MouseEvent mouseEvent)
    {
    }

    public void onTrombinoscopeClicked(MouseEvent mouseEvent) {
    }

}
