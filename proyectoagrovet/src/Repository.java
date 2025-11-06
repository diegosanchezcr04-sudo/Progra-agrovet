
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author leonardo
 */
public class Repository {

    File file = new File("products.txt");

    private Product products[] = new Product[100];
    private byte counter;

    public Repository() {
    }

    public void createFile() {

        try {
            if (file.createNewFile()) {
                System.out.println("File " + file.getName() + "; size: " + file.length()
                        + " created successfully.");
            } else {
                System.out.println("File aready " + file.getName() + " ;size"
                        + file.length() + " already exists.");
            }

        } catch (IOException e) {
            System.out.println("Error to create the file.." + e.getMessage());
        }

    }

    public void loadProducts() {

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

    public void saveProducts() {

        try ( PrintWriter pw = new PrintWriter("products.txt")) {
            for (byte i = 0; i < counter; i++) {
                pw.println(products[i].toFileLine());
            }
            System.out.println(counter + " saved succsessfully.");

        } catch (IOException e) {
            System.out.println("Error saving the products:" + e.getMessage());

        }

    }

    public void addProducts(Product product) {

        if (counter < products.length) {
            products[counter++] = product;
            System.out.println("Product added: " + product.getName());
        }
    }

    public void deleteProductsFile() {
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
