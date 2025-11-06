    class Customer extends Person {

    private float totalPurchases;
    private Invoice purchaseHistory;
    private Pet[] pets;

    public Customer() {
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
