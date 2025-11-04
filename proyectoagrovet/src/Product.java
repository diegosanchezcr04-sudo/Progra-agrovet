
import java.util.Date;

public class Product {

    private String name;

    private String id;

    private String description;

    private float discount;

    private float tax;

    private float price;

    private int barCode;

    private Type type;

    private Category category;

    private int stockQuantity;

    private Date expirationDate;

    private Date startDay;

    public boolean isExpired() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int reduceStock(int quantity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int increaseStock(int quantity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void untitledMethod() {
    }

    public boolean isInStock() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
