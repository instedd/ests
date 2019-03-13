package ug.co.sampletracker.app.models;

import java.util.Date;

import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.general.DateHandler;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/28/2018.
 */

public class StatementTxn {

    private String txnType;
    private String transactionAmount;
    private String txnIdOrInvoiceNo;
    private String txnDate;
    private String status;
    private String fromAccount;

    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private Date date;
    private String readableDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTxnIdOrInvoiceNo() {
        return txnIdOrInvoiceNo;
    }

    public void setTxnIdOrInvoiceNo(String txnIdOrInvoiceNo) {
        this.txnIdOrInvoiceNo = txnIdOrInvoiceNo;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getReadableDate() {
        return new DateHandler().getHumanReadableDate(date, ConstStrings.DATE_FORMAT_TXN);
    }

}
