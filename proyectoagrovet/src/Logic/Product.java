package Logic;

import java.util.Date;

public class Product {

    private float discount;
    private float price;
    private float tax;

    private int barCode;
    private short stockQuantity;

    private Date expirationDate;

    private String description;
    private String id;
    private String name;

    private Category category;
    private Type type;

    public Product() {
    }

    public Product(float discount, float price, float tax, int barCode,
            short stockQuantity, Date expirationDate, String description,
            String id, String name, Category category, Type type) {

        this.discount = discount;
        this.price = price;
        this.tax = tax;
        this.barCode = barCode;
        this.stockQuantity = stockQuantity;
        this.expirationDate = expirationDate;
        this.description = description;
        this.id = id;
        this.name = name;
        this.category = category;
        this.type = type;
    }

    public String toFileLine() {
        return discount + ";" + price + ";" + tax + ";" + barCode + ";"
                + stockQuantity + ";" + expirationDate.getTime() + ";"
                + description + ";" + id + ";" + name + ";"
                + category + ";" + type;
    }

    public static Product fromFileLine(String line) {

        try {
            String[] data = line.split(";");
            if (data.length != 11) {
                throw new IllegalArgumentException("Line " + line + " Corrupted.");
            }

            return new Product(
                    Float.parseFloat(data[0]), // discount
                    Float.parseFloat(data[1]), // price  
                    Float.parseFloat(data[2]), // tax
                    Integer.parseInt(data[3]), // barCode
                    Short.parseShort(data[4]), // stockQuantity
                    new Date(Long.parseLong(data[5])), // expirationDate
                    data[6], // description
                    data[7], // id
                    data[8], // name
                    Category.valueOf(data[9]), // category
                    Type.valueOf(data[10]) //Type
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Error to process line:" + e.getMessage());
            return null;

        }

    }

    public String getName() {
        return name;
    }

    public float getDiscount() {
        return discount;
    }

    public float getPrice() {
        return price;
    }

    public float getTax() {
        return tax;
    }

    public int getBarCode() {
        return barCode;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }

    public boolean isExpired(Product products[]) {
        for (byte i = 0; i < products.length; i++) {
            if (products[i] != null) {
                System.out.println("Product " + name + " is not expired");
                return false;
            } else {
                System.out.println("Product " + name + " is expired.");
            }

        }
        return true;
    }

    public int reduceStock(int quantity) {
        return 0;

    }

    public int increaseStock(int quantity) {

        return 0;

    }

    public boolean isInStock() {

        return false;

    }
}

