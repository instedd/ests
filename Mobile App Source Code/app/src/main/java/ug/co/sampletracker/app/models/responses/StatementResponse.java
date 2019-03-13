package ug.co.sampletracker.app.models.responses;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.models.Order;
import ug.co.sampletracker.app.models.Payment;
import ug.co.sampletracker.app.utils.constants.EnumStmtReceiptionTypes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/8/2018.
 */

public class StatementResponse extends Response {

    public List<Order> orders = new ArrayList<>();
    public List<Payment> payments = new ArrayList<>();
    public String receiptionType = EnumStmtReceiptionTypes.MOBILE_APP.getType();

    public List<Order> getOrders() {
        return orders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public String getReceiptionType() {
        return receiptionType;
    }

    public void setReceiptionType(String receiptionType) {
        this.receiptionType = receiptionType;
    }
}
