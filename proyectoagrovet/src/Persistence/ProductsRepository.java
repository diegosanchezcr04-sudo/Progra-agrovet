
package Persistence;

import Logic.Product;
import Logic.Category;
import Logic.Type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;


/**
 *
 * @author leonardo
 */
public class ProductsRepository extends Repository {

    private final Product products[] = new Product[100];
    private byte counter;

    @Override
    public void createFile() {
        File file = new File("products.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File " + file.getName() + "; size: "
                        + file.length()
                        + " created successfully.");
            } else {
                System.out.println("File aready " + file.getName() + " ;size"
                        + file.length() + " already exists.");
            }

        } catch (IOException e) {
            System.out.println("Error to create the file.." + e.getMessage());
        }

    }

    @Override
    public void addItem() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Insert the products quantity to add: ");
            byte quantity = Byte.parseByte(br.readLine());

            for (int i = 0; i < quantity; i++) {
                System.out.println("""
                                   --------------------------------------- 
                                   b>>>> Product #""" + (i + 1) + " <<<<");

                System.out.print("Name: ");
                String name = br.readLine();

                System.out.print("Description: ");
                String description = br.readLine();

                System.out.print("ID: ");
                String id = br.readLine();

                System.out.print("Price: ");
                float price = Float.parseFloat(br.readLine());

                System.out.print("Discount: ");
                float discount = Float.parseFloat(br.readLine());

                System.out.print("Tax: ");
                float tax = Float.parseFloat(br.readLine());

                System.out.print("Bar code: ");
                int barCode = Integer.parseInt(br.readLine());

                System.out.print("Stock quantity: ");
                byte stockQuantity = Byte.parseByte(br.readLine());

                Product product = new Product(discount, price, tax, barCode,
                        stockQuantity, new Date(), description, id, name,
                        Category.PET_FOOD, Type.DOG_FOOD);

                if (counter < products.length) {
                    products[counter++] = product;
                    System.out.println("Product added: " + product.getName());

                }

            }

        } catch (IOException e) {
            System.out.println("Input error: " + e.getMessage());
            
        } catch (NumberFormatException e) {
        System.out.println("Invalid number format: " + e.getMessage());
        
        } catch (Exception e) {
        System.out.println("Unexpected error: " + e.getMessage());
    }

    }

    @Override
    public void loadItem() {
        try ( BufferedReader br = new BufferedReader(
                new FileReader("products.txt"))) {

            String line;
            byte tempCounter = 0;
            while ((line = br.readLine()) != null && tempCounter
                    < products.length) {
                Product loadedProduct = Product.fromFileLine(line);
                if (loadedProduct != null) {
                    products[tempCounter++] = loadedProduct;
                    counter = tempCounter;
                }
            }

        } catch (IOException e) {
            System.out.println("Error to load the file:");
        }

    }

    @Override
    public void saveItem() {
        try ( PrintWriter pw = new PrintWriter("products.txt")) {
            for (byte i = 0; i < counter; i++) {
                pw.println(products[i].toFileLine());
            }
            System.out.println(counter + " saved succsessfully.");

        } catch (IOException e) {
            System.out.println("Error saving the products:" + e.getMessage());

        }

    }

    @Override
    public void deleteFile() {
        File file = new File("products.txt");
        if (file.exists()) {

            if (file.delete()) {
                System.out.println("File " + file.getName() + " deleted.");
                counter = 0;
            } else {
                System.out.println("Failed to delete the file.");
            }

        } else {
            System.out.println("File does not exists.");
        }

    }

}
