package ug.co.sampletracker.app.models.responses;

import ug.co.sampletracker.app.utils.constants.EnumPaymentTypes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/8/2018.
 */

public class PaymentResponse extends Response {

    private String transactionNo = "";
    private String paymentMethod = EnumPaymentTypes.MOBILE_MONEY.getType();

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }
}
