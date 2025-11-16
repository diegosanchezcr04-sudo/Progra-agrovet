package logic;

import Persistence.ProductsRepository;
import Persistence.Repository;

import java.util.Scanner;

/**
 *
 * @author leonardo
 */
public class Main {

    public static void main(String[] args){
        Repository repoP = new ProductsRepository();

        Scanner sc = new Scanner(System.in);
        
        System.out.println("loading existends products...");
        repoP.loadItem();
       
        byte op = 0;
        do {

            System.out.print("""
            =============================
              >>>>> Products Menu <<<<<  
            =============================
            1. Crate products File.
            2. Add products.
            3. Load products.
            4. Save products.
            5. Delete products.
            6. Exit.   
            =============================
             Select one:
                           """);

            op = sc.nextByte();

            switch (op) {
                
                case 1 -> repoP.createFile();

                case 2 -> repoP.addItem();
                
                case 3 -> repoP.loadItem();

                case 4 ->  repoP.saveItem();

                case 5 -> repoP.deleteFile();

                case 6 -> { 
                    System.out.println("Saving before to exits.");
                    repoP.saveItem();
                    System.exit(0);
                }
                

                default -> System.out.println("Invalid option.");

            }

        } while (op != 6);
    }
}
