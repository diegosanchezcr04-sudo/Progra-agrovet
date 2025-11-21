package logic;

import Persistence.InvoiceRepository;
import Persistence.ProductsRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final ProductsRepository productsRepo
            = new ProductsRepository();
    private static final InvoiceRepository invoiceRepo
            = new InvoiceRepository();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //Test data
    private static final Customer[] customers = new Customer[10];
    private static final Employee[] employees = new Employee[5];
    private static int customerCount = 0;
    private static int employeeCount = 0;

    public static void main(String[] args) throws IOException {
        initializeSampleData();
        invoiceRepo.loadItem();
        showMainMenu();

    }

    /**
     * Initialize the default employees and customer data for test the pos.
     */
    private static void initializeSampleData() {

        employees[employeeCount++] = new Employee(true, Employee.EmployeeRole.Cashier, "123",
                "Guanacaste", "diegosudo@agrovet.com", "EMP001", "Diego Sanchez",
                "8888-8888");
        employees[employeeCount++] = new Employee(true, Employee.EmployeeRole.Admin, "admin",
                "San Jose", "leoaguilar@agrovet.com", "EMP002", "Leo Aguilar",
                "7777-7777");

        customers[customerCount++] = new Customer("Alajuela", "edu@email.com",
                "C001", "Edu Barrantes", "1111-1111");
        customers[customerCount++] = new Customer("Cartago",
                "leandro@email.com", "C002", "Leandro Marin", "2222-2222");

        System.out.println("Sample sample data initialized");
    }

    /**
     * POS agrovet Main menu
     */
    private static void showMainMenu() throws IOException {
        while (true) {
            System.out.print("""
                =============================
                  >>>>> AGROVET POS <<<<<  
                =============================
                1. Process sell.
                2. Manege Productos.
                3. Manege Customers
                4. Manege Employees
                5. Generate sell report
                6. Exit
                =============================
                 Choose option: """);

            byte option = Byte.parseByte(br.readLine());

            switch (option) {
                case 1 ->
                    processSale();
                case 2 ->
                    showProductsMenu();
                case 3 ->
                    manageCustomers();
                case 4 ->
                    manageEmployees();
                case 5 ->
                    showSalesReports();
                case 6 -> {
                    System.exit(0);
                }
                default ->
                    System.out.println("Invalid option");
            }
        }
    }

    /**
     * Process sell full
     */
    private static void processSale() throws IOException {
        System.out.println("""
                           ======================
                            <<< PROCESS SELL >>>
                           ======================
                           """);

        Customer customer = selectCustomer();
        if (customer == null) {
            return;
        }

        Employee cashier = selectCashier();
        if (cashier == null) {
            return;
        }

        Cart cart = new Cart(customer);

        addProductsToCart(cart);

        if (cart.isEmpty()) {
            System.out.println("Cart empty - canceled sell");
            return;
        }

        System.out.println("\n" + cart.getCartSummary());

        processPayment(cart, customer, cashier);
    }

    /**
     * Choose customer
     */
    private static Customer selectCustomer() throws IOException {
        System.out.println("""
                           ========================
                            <<< Selec Customer >>>
                           ========================
                           """);
        for (int i = 0; i < customerCount; i++) {
            System.out.println((i + 1) + ". " + customers[i].getDetails());
        }
        System.out.print("Choose new customer: ");

        byte choice = Byte.parseByte(br.readLine());

        if (choice == 0) {
            return createNewCustomer();
        } else if (choice > 0 && choice <= customerCount) {
            return customers[choice - 1];
        } else {
            System.out.println("Ivalid choice.");
            return null;
        }
    }

    /**
     * Create new Customer
     */
    private static Customer createNewCustomer() throws IOException {
        System.out.print("Name: ");
        String name = br.readLine();
        System.out.print("Email: ");
        String email = br.readLine();
        System.out.print("Phone: ");
        String phone = br.readLine();
        System.out.print("Address: ");
        String address = br.readLine();
        System.out.print("ID: ");
        String id = br.readLine();

        if (customerCount < customers.length) {
            Customer newCustomer = new Customer(address, email, id, name, phone);
            customers[customerCount++] = newCustomer;
            System.out.println("Customer created: "
                    + newCustomer.getDetails());
            return newCustomer;
        } else {
            System.out.println("Customer limit reached.");
            return null;
        }
    }

    /**
     * Select Cahier
     */
    private static Employee selectCashier() throws IOException {
        System.out.println("""
                           ========================
                            <<< Select Cashier >>>
                           ========================
                           """);
        int cashierCount = 0;
        Employee[] cashiers = new Employee[employeeCount];

        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getRole() == Employee.EmployeeRole.Cashier
                    && employees[i].isActive()) {
                cashiers[cashierCount++] = employees[i];
                System.out.println((cashierCount) + ". "
                        + employees[i].getDetails());
            }
        }

        if (cashierCount == 0) {
            System.out.println("There are no spaces available.");
            return null;
        }

        System.out.print("Choice cashier: ");
        byte choice = Byte.parseByte(br.readLine());

        if (choice > 0 && choice <= cashierCount) {
            return cashiers[choice - 1];
        } else {
            System.out.println("Invalid choice.");
            return null;
        }
    }

    /**
     * Add productos to cart
     */
    private static void addProductsToCart(Cart cart) throws IOException {
        System.out.println("""
                          ======================
                           <<< Add products >>>
                          ======================
                           """);

        Product sampleProduct = new Product(10, 100, 13, 123456, (short) 50,
                null, "Dewormer for dogs", "PROD001", "Antiparasitary",
                Product.Category.VETERINARY_MEDICINE, Product.Type.PARASITICIDE);

        System.out.print("Quantity of: " + sampleProduct.getName() + " to add: ");
        short quantity = Short.parseShort(br.readLine());

        try {
            cart.addProduct(sampleProduct, quantity);
            System.out.println("Product added to cart.");
        } catch (Exception e) {
            System.out.println("Error to add product: " + e.getMessage());
        }
    }

    /**
     * Process payment
     */
    private static void processPayment(Cart cart, Customer customer,
            Employee cashier) throws IOException {
        System.out.println(" <<< PROCESS PAYMENT >>>");
        System.out.println("Total to pay: " + cart.getTotal());

        System.out.print("""
            Payment method:
            1. Cash
            2. Card
            Select: """);

        byte methodChoice = Byte.parseByte(br.readLine());

        Payment.Method method;
        String reference;

        if (methodChoice == 1) {
            method = Payment.Method.CASH;
            reference = "CASH.";
        } else {
            method = Payment.Method.CARD;
            System.out.print("Card number: ");
            reference = br.readLine();
        }

        Payment payment = new Payment((float) cart.getTotal(), method, reference);

        if (payment.ProcessPayment()) {

            Invoice invoice = new Invoice(customer, cashier, cart);
            invoice.paid();

            invoiceRepo.addInvoice(invoice);
            invoiceRepo.saveItem();

            System.out.println("Successfull Sell!");
            System.out.println(invoice.getInvoiceDetails());
            System.out.println(payment.getPaymentInfo());

            cart.close();
        } else {
            System.out.println("Failed Payment!");
        }
    }

    private static void showSalesReports() throws IOException {
        System.out.println(" <<< SALES REPORTS >>>");
        System.out.println("Total invoices: " + invoiceRepo.getCount());
        System.out.println("Total sales: " + invoiceRepo.getTotalSales());
        System.out.println("Last invoice saved to file");
    }

    /**
     * Products Menu
     */
    private static void showProductsMenu() throws IOException {
        while (true) {
            System.out.print("""
                =============================
                  >>>>> Menu Products <<<<<  
                =============================
                1. Create products file
                2. Add products
                3. Load products  
                4. Save products
                5. Delete products
                6. Back to main menu
                =============================
                 Select option: """);

            byte option = Byte.parseByte(br.readLine());

            switch (option) {
                case 1 ->
                    productsRepo.createFile();
                case 2 ->
                    productsRepo.addItem();
                case 3 ->
                    productsRepo.loadItem();
                case 4 ->
                    productsRepo.saveItem();
                case 5 ->
                    productsRepo.deleteFile();
                case 6 -> {
                    return;
                }
                default ->
                    System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Manege customers
     */
    private static void manageCustomers() {
        System.out.println("\n <<< CUSTOMER MANAGER >>>");
        for (int i = 0; i < customerCount; i++) {
            System.out.println((i + 1) + ". " + customers[i].getDetails());
        }
    }

    /**
     * Manege Employees
     */
    private static void manageEmployees() {
        System.out.println("\n <<< EMPLOYEE MANAGER >>> ");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println((i + 1) + ". " + employees[i].getDetails());
        }
    }

}
