package logic;

public class Customer extends Person {

    public Customer() {
    }

    public Customer(String address, String email, String id, String name, String phone) {
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
