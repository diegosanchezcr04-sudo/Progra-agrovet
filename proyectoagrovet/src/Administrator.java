
import java.util.Date;

public class Administrator extends Employee {

    private Date startDay;

    private Date finishDate;

    private Status status;

    private String accessLevel;

    private String[] managedDepartments;

    private boolean systemModifier;

    public boolean addEmployee() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean removeEmployee() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String generateFinancialReport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateSystemSettings() {
    }

    public boolean backupDatabase() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
