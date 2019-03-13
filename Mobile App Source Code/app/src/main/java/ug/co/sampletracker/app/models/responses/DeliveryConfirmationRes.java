package ug.co.sampletracker.app.models.responses;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.models.Order;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/14/2018.
 */

public class DeliveryConfirmationRes extends Response {

    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
