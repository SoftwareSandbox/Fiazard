package be.swsb.fiazard.ordering.orderplacement;

import java.util.List;

public class PlaceOrder {

    private List<Sandwich> sandwiches;

    public PlaceOrder() {
    }

    public PlaceOrder(List<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }
}
