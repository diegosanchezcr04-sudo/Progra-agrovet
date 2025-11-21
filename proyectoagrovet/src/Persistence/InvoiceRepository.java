package Persistence;

import logic.Invoice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Represent the repository when the invoices are stored
 *
 * @author Leo Aguilar
 * @author Eduardo Barrantes
 */
public class InvoiceRepository extends Repository {

    private final Invoice invoices[] = new Invoice[100];
    private byte counter = 0;

    /**
     * Create the invoices file
     */
    @Override
    public void createFile() {
        File file = new File("invoice.txt");

        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("Error creating invoice file: " + e.getMessage());
        }
    }

    /**
     * Autogenerate invoices in file
     *
     * @param invoice
     */
    public void addInvoice(Invoice invoice) {
        if (counter < invoices.length) {
            invoices[counter++] = invoice;
            System.out.println("Invoice saved to memory: " + invoice.getNumber());
        } else {
            System.out.println("Invoice storage full");
        }
    }

    /**
     * Load the invoices in file
     */
    @Override
    public void loadItem() {
        try ( BufferedReader br = new BufferedReader(new FileReader("invoice.txt"))) {

            String line;
            byte tempCounter = 0;

            while ((line = br.readLine()) != null && tempCounter
                    < invoices.length) {
                Invoice inv = Invoice.fromFileLine(line);

                if (inv != null) {
                    invoices[tempCounter++] = inv;
                    counter = tempCounter;
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading invoice file.");
        }
    }

    /**
     * Save the invoices in file
     */
    @Override
    public void saveItem() {
        try ( PrintWriter pw = new PrintWriter("invoice.txt")) {

            for (byte i = 0; i < counter; i++) {
                pw.println(invoices[i].toFileLine());
            }

            System.out.println(counter + " invoices saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving invoices: " + e.getMessage());
        }
    }

    /**
     * Delete the invoices file
     */
    @Override
    public void deleteFile() {
        File file = new File("invoice.txt");

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Invoice file deleted.");
                counter = 0;
            } else {
                System.out.println("Error deleting file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    /**
     * Get total sales for reports
     */
    public float getTotalSales() {
        double total = 0;
        for (byte i = 0; i < counter; i++) {
            total += invoices[i].getTotal();
        }
        return (float) total;
    }

    /**
     * Get invoices count
     *
     * @return invoice counter
     */
    public byte getCount() {
        return counter;
    }

    //This method is abstract, but is not used.
    @Override
    public void addItem() {

    }

}
