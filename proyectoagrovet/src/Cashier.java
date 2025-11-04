public class Cashier extends Employee {

    private byte dailySalesCount;

    private float dailySalesTotal;

    public Invoice processSale() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void applyDiscoun() {
    }

    public String generateDailyReport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean cancelTransaction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
