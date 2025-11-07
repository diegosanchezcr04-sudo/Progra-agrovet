
import java.util.Date;

public class Cashier extends Employee {

    private byte dailySalesCount;
    private float dailySalesTotal;

    public Cashier(float salary, Date hireDate, String workingStation, String 
            departament, Status status, Schedule schedule, String adress, 
            String email, String id, String name, String phoneNumber, 
            Date registrationDate) {
        super(salary, hireDate, workingStation, departament, status, schedule, 
                adress, email, id, name, phoneNumber, registrationDate);
    }
    
    public Invoice processSale() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void applyDiscoun() {
    }

    public String generateDailyReport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean cancelTransaction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
