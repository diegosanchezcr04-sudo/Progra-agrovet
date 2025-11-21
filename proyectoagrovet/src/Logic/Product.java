package logic;

import java.util.Date;

/**
 * Represent to the products in the agrovet POS
 *
 * @author Leo Aguilar
 */
public class Product {

    private float discount;
    private float price;
    private float tax;

    private int barCode;
    private short quantity;

    private Date expirationDate;

    private String description;
    private String id;
    private String name;

    private Category category;
    private Type type;

    public Product() {
    }

    /**
     * Initialize the product´s atributes
     *
     * @param discount
     * @param price
     * @param tax
     * @param barCode
     * @param quantity
     * @param expirationDate
     * @param description
     * @param id
     * @param name
     * @param category
     * @param type
     */
    public Product(float discount, float price, float tax, int barCode,
            short quantity, Date expirationDate, String description,
            String id, String name, Category category, Type type) {

        this.discount = discount;
        this.price = price;
        this.tax = tax;
        this.barCode = barCode;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.description = description;
        this.id = id;
        this.name = name;
        this.category = category;
        this.type = type;
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

    public short getQuantity() {
        return quantity;
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

    /**
     * Turns the product data in file.txt format
     *
     * @return prodduct data in file.txt format
     */
    public String toFileLine() {
        return discount + ";" + price + ";" + tax + ";" + barCode + ";"
                + quantity + ";" + (expirationDate != null ? expirationDate
                                .getTime() : "0") + ";" + description + ";" + id + ";"
                + name + ";" + category + ";" + type;
    }

    /**
     * Set the products in a string arry for load it from the file
     *
     * @param line
     * @return produt´s data
     */
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
                    Short.parseShort(data[4]), // quantity
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

    /**
     * Validates if the product is expired
     *
     * @return experation date
     */
    public boolean isExpired() {
        if (expirationDate == null) {
            return false;
        }
        return expirationDate.before(new Date());
    }

    /**
     * Varify if product is in stock
     *
     * @return true or false
     */
    public boolean isInStock() {
        return this.quantity > 0;
    }

    /**
     * Calculate the productos final price
     *
     * @return Final price
     */
    public float getFinalPrice() {
        float priceAfterDiscount = price - (price * discount / 100);
        return priceAfterDiscount + (priceAfterDiscount * tax / 100);
    }

    /**
     * Give the products information
     *
     * @return
     */
    public String getInfo() {
        return "Product: " + name
                + " | ID: " + id
                + " | Price: " + price
                + " | Final: " + getFinalPrice()
                + " | Stock: " + quantity
                + " | Category: " + category;
    }

    /**
     * Represent the products´categories
     */
    public enum Category {

        VETERINARY_MEDICINE,
        PET_FOOD,
        LIVESTOCK_FOOD,
        ANIMAL_ACCESSORIES,
        AGRO_SUPPLIES,
        ANIMAL_HYGIENE,
        FARM_EQUIPMENT,
        PETS
    }

    /**
     * Represent the products´types
     */
    public enum Type {

        ANTIBIOTIC, VACCINE, PARASITICIDE, ANALGESIC, VITAMIN_SUPPLEMENT,
        DOG_FOOD, CAT_FOOD, BIRD_FOOD, FISH_FOOD,
        PET_TREATS, CATTLE_FEED, POULTRY_FEED, PIG_FEED, HORSE_FEED,
        COLLAR, LEASH, FEEDER, PET_BED, PET_TOY,
        SEEDS, FERTILIZER, PESTICIDE, AGRO_TOOLS, PET_SHAMPOO,
        DISINFECTANT, FLEA_CONTROL;
    }

}
