package logic;

/**
 * Represents a customer in the agrovet system. Contains basic customer
 * information.
 *
 * @author Leo Aguilar
 */
class Customer extends Person {

    public Customer() {
    }

    /**
     * Initialize the customer atributes
     *
     * @param address
     * @param email
     * @param id
     * @param name
     * @param phone
     */
    public Customer(String address, String email, String id, String name,
            String phone) {
        super(address, email, id, name, phone);
    }

    @Override
    public String getDetails() {
        return "Customer: " + name;

    }

    @Override
    public String toFileLine() {
        return "CUSTOMER;" + id + ";" + name + ";" + email + ";" + phone;
    }

}
