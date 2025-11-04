public class Veterinarian extends Employee {

    private String licenseNumber;

    private String specialty;

    private byte maxDailyAppointments;

    private String[] availableSlots;

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
