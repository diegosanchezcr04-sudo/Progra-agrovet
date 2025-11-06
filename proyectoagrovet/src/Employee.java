
import java.util.Date;

public class Employee extends Person  {

    protected  float salary;
    protected Date hireDate;
    protected  String workingStation;
    protected  String departament;
    protected  Status status;
    protected  Schedule schedule;
    
    public byte calculateSiniority() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean loginSystem() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void logoutSystem() {
    }

    public void updateSchedule() {
    }
}
