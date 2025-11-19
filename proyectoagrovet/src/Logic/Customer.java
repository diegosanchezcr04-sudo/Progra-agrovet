package logic;

/**
 * Represents a customer in the agrovet system.
 * Contains basic customer information.
 * 
 * @author Leo Aguilar
 */
    class Customer extends Person {
   
    public Customer() {
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
