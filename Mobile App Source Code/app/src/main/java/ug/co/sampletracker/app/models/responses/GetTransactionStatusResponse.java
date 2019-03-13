package ug.co.sampletracker.app.models.responses;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 6/6/2018.
 */

public class GetTransactionStatusResponse extends Response {

    public String transactionStatus;

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
