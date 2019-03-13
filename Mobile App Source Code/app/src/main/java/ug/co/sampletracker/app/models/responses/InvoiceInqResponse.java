package ug.co.sampletracker.app.models.responses;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.models.DataInvoice;

public class InvoiceInqResponse extends Response{

    private List<DataInvoice> invoices = new ArrayList<>();

    public List<DataInvoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<DataInvoice> invoices) {
        this.invoices = invoices;
    }
    
}
