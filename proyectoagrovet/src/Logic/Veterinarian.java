package logic;

import java.util.Date;

public class Veterinarian extends Employee {

    private String licenseNumber;
    private String specialty;
    private byte maxDailyAppointments;
    private String[] availableSlots;

    public Veterinarian(float salary, Date hireDate, String workingStation, 
            String departament, Status status, Schedule schedule, 
            String adress, String email, String id, String name, 
            String phoneNumber, Date registrationDate) {
        super(salary, hireDate, workingStation, departament, status, schedule, 
                adress, email, id, name, phoneNumber, registrationDate);
    }
    
    

    public boolean scheduleAppointment() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean cancelAppointment() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean prescribeTreatment() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateMedicalRecord() {
    }

    public String[] getAvailableSlots() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isSlotAvailable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
