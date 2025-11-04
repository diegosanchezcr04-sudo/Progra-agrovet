
import java.util.Date;

public abstract class Person  {

    private String name;

    private String phoneNumber;

    private String id;

    private String email;

    private String adress;

    private Date registrationDate;

    public String getRole() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float getDiscountRate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String generateReport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean validateData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateContactInfo() {
    }

    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
