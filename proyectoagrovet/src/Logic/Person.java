package logic;

import java.util.Date;

public class Person  {

    protected String adress;
    protected String email;
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected Date registrationDate;

    public Person() {
    }

    public Person(String adress, String email, String id, String name, 
            String phoneNumber, Date registrationDate) {
        this.adress = adress;
        this.email = email;
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
    }
    
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
