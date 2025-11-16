package logic;

import java.util.Date;

public class Employee extends Person  {

    protected  float salary;
    protected  Date hireDate;
    protected  String workingStation;
    protected  String departament;
    protected  Status status;
    protected  Schedule schedule;

    public Employee(float salary, Date hireDate, String workingStation, 
            String departament, Status status, Schedule schedule, 
            String adress, String email, String id, String name, 
            String phoneNumber, Date registrationDate) {
        super(adress, email, id, name, phoneNumber, registrationDate);
        this.salary = salary;
        this.hireDate = hireDate;
        this.workingStation = workingStation;
        this.departament = departament;
        this.status = status;
        this.schedule = schedule;
    }
    
    
    
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
