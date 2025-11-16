package logic;

import java.util.Date;

    class Customer extends Person {

    private float totalPurchases;
    private Invoice purchaseHistory;
    private Pet[] pets;


    public Customer(float totalPurchases, Invoice purchaseHistory, Pet[] pets, 
            String adress, String email, String id, String name, String 
                    phoneNumber, Date registrationDate) {
        super(adress, email, id, name, phoneNumber, registrationDate);
        this.totalPurchases = totalPurchases;
        this.purchaseHistory = purchaseHistory;
        this.pets = pets;
    }
    
    
    
    public boolean addPet() {
        return false;
    }

    public boolean removePet() {
        return false;
    }

    public Pet[] getPets() {
        return null;
    }

    public Invoice makePurchase() {
        return null;
    }

    public Invoice getPurchaseHistory() {
        return null;
    }
}
