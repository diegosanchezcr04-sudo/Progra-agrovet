package logic;

/**
 * Represents a person in the agrovet system and shares common attributes and
 * methods. Contains basic people information used by both customers and
 * employees.
 *
 * @author Leo Aguilar
 */
public abstract class Person {

    protected String address;
    protected String email;
    protected String id;
    protected String name;
    protected String phone;

    public Person() {
    }

    /**
     * This constructor initialize the Person's atributes
     *
     * @param address
     * @param email
     * @param id
     * @param name
     * @param phone
     */
    public Person(String address, String email, String id, String name,
            String phone) {
        this.address = address;
        this.email = email;
        this.id = id;
        this.name = name;
        this.phone = phone;

    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    /**
     * Gets people´s details asocied with the agrovet
     *
     * @return people´s details (customers, employees).
     */
    public abstract String getDetails();

    /**
     * Turns the people´s data in file.txt format
     *
     * @return people´s data in file.txt format.
     */
    public abstract String toFileLine();

}
