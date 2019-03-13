package ug.co.sampletracker.app.models;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/24/2018.
 */
public class DataTonnage {

    private String customerRef;
    private String quantity;
    private String date;

    public String getCustomerRef() {
        return customerRef;
    }

    public void setCustomerRef(String customerRef) {
        this.customerRef = customerRef;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
