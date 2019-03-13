package ug.co.sampletracker.app.models.requests;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/8/2018.
 */

public class StatementRequest {

    public String showOrders = Boolean.TRUE.toString();
    public String showPayments = Boolean.TRUE.toString();
    public String customerReferenceNo = "";
    public String statementType = "";
    public String transactionNo = "";
    public String email = "";
    public String startDate = "";
    public String endDate = "";
    public String receptionType = "";

    public String getReceptionType() {
        return receptionType;
    }

    public void setReceptionType(String receptionType) {
        this.receptionType = receptionType;
    }

    public String getShowOrders() {
        return showOrders;
    }

    public void setShowOrders(String showOrders) {
        this.showOrders = showOrders;
    }

    public String getShowPayments() {
        return showPayments;
    }

    public void setShowPayments(String showPayments) {
        this.showPayments = showPayments;
    }

    public String getCustomerReferenceNo() {
        return customerReferenceNo;
    }

    public void setCustomerReferenceNo(String customerReferenceNo) {
        this.customerReferenceNo = customerReferenceNo;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
