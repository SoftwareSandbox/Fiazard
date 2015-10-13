package be.swsb.fiazard.ordering.orderplacement;

public class PlaceOrder {

    private String sandwich;

    public PlaceOrder(){}

    public PlaceOrder(String sandwich) {
        this.sandwich = sandwich;
    }

    public String getSandwich() {
        return sandwich;
    }

    public void setSandwich(String sandwich) {
        this.sandwich = sandwich;
    }
}
