package logic;

/**
 * Represent to agrovet employees. Contains basic employee information and their
 * system permissions.
 *
 * @author Leo Aguilar
 */
public class Employee extends Person {

    /**
     * Indicates if the employees can access to the system
     */
    private boolean active = true;
    private EmployeeRole role;
    private String password;

    public Employee() {
    }

    /**
     * Initialize the Employee atributies and validates the employee role and
     * password.
     *
     * @param active
     * @param role
     * @param password
     * @param address
     * @param email
     * @param id
     * @param name
     * @param phone
     */
    public Employee(boolean active, EmployeeRole role, String password,
            String address, String email, String id, String name,
            String phone) {
        super(address, email, id, name, phone);

        if (role == null) {
            throw new IllegalArgumentException("Employee role cannot be null");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.active = active;
        this.role = role;
        this.password = password;
    }

    /**
     * Give the employees´details.
     *
     * @return employees´details.
     */
    @Override
    public String getDetails() {
        return name + " - " + role;
    }

    /**
     * Turns the employees´s information in file.txt format for save in files.
     *
     * @return the employees´atributes in file.txt format.
     */
    @Override
    public String toFileLine() {
        return "EMPLOYEE;" + id + ";" + name + ";" + email + ";"
                + phone + ";" + address + ";" + role + ";" + active;
    }

    /**
     * Authenticates employee with provided password
     *
     * @param inputPassword
     * @return status active and password compared inputPassword
     */
    public boolean authenticate(String inputPassword) {
        return this.active && this.password.equals(inputPassword);
    }

    public boolean isActive() {
        return active;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Allow set password if the current password is not empty
     *
     * @param newPassword
     */
    public void setPassword(String newPassword) {
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            this.password = newPassword;
        }
    }

    /**
     * Represent the employee's role in the agrovet POS.
     *
     * @author Leo Aguilar
     */
    public enum EmployeeRole {
        Admin,
        Cashier,

    }
}
