
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author leonardo
 */
public class Main {

    public static void main(String[] args) {
       Repository repo = new Repository();

        Scanner sc = new Scanner(System.in);
        
        Product product1 = new Product((float) 0.1, 12500, (float) 0.13, 123456, 
                50, new Date(), "Croquetas para perro", "P001",
                "Croquetas Premium", Category.PET_FOOD,
                Type.DOG_FOOD);
        
       
        
        System.out.print("Insert the products quantity that you wish add.");
         byte stockQuantity = sc.nextByte();
         
        Product products[] = new Product[stockQuantity];
        
        byte op = 0;
        do {
        
            //Implementé el menú.
        System.out.print("""
            =============================
              >>>>> Products Menu <<<<<  
            =============================
            1. Crate products File.
            2. Load products.
            3. Add products.
            4. Save products.
            5. Delete products.
            6. Exit.   
            =============================
             Select one:
                           """);
        
        op = sc.nextByte();
        
        switch (op) { 
        case 1 -> repo.createFile();
        
        case 2 -> repo.loadProducts();
        
        case 3 -> repo.addProducts(product1);
        
        case 4 -> repo.saveProducts();
        
        case 5 -> repo.deleteProductsFile();
        
        case 6 -> System.exit(0);
        
        default -> System.out.println("Invalid option.");
            
        }
        
        } while (op != 6);
    }   
}

