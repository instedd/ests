package ug.co.sampletracker.app.models.responses;

import ug.co.sampletracker.app.utils.constants.EnumPaymentTypes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/10/2018.
 */

public class PaymentUnreferencedResponse extends  Response {

    public String channelTxnId = "";
    private String transactionNo ="";
    public String getTransactionNo() {
        return transactionNo;
    }
    public String paymentMethod = EnumPaymentTypes.MOBILE_MONEY.getType();

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getChannelTxnId() {
        return channelTxnId;
    }

    public void setChannelTxnId(String channelTxnId) {
        this.channelTxnId = channelTxnId;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }
}
