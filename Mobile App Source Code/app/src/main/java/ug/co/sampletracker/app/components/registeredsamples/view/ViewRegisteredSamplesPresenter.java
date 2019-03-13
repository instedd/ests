package ug.co.sampletracker.app.components.registeredsamples.view;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import ug.co.sampletracker.app.connections.dataloaders.DTMiniStatement;
import ug.co.sampletracker.app.database.DataManager;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.Order;
import ug.co.sampletracker.app.models.Payment;
import ug.co.sampletracker.app.models.StRegisteredSamplePojo;
import ug.co.sampletracker.app.models.StatementTxn;
import ug.co.sampletracker.app.models.requests.StatementRequest;
import ug.co.sampletracker.app.models.responses.StatementResponse;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumStatementTypes;
import ug.co.sampletracker.app.utils.constants.EnumStmtReceiptionTypes;
import ug.co.sampletracker.app.utils.constants.EnumTransactionTypes;
import ug.co.sampletracker.app.utils.constants.StatusCodes;
import ug.co.sampletracker.app.utils.general.DateHandler;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/28/2018.
 */

public class ViewRegisteredSamplesPresenter implements DTMiniStatement.ServerResponseMiniStatementListener {

    private ViewRegisteredSamplesView view;
    private ViewRegisteredSamplesInteractor interactor;
    private PreferencesDb preferencesDb;

    ViewRegisteredSamplesPresenter(ViewRegisteredSamplesView view, ViewRegisteredSamplesInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void setPreferencesDb(PreferencesDb preferencesDb) {
        this.preferencesDb = preferencesDb;
    }

    void loadRegisteredSamples() {

        view.startProgressDialog();

        List<StRegisteredSamplePojo> registeredSamplePojos = DataManager.appData.getRegisteredSamples();
        view.displayRegisteredSamples(registeredSamplePojos);

        view.stopProgressDialog();

    }

    @NonNull
    private StatementRequest getStatementRequest() {

        StatementRequest request = new StatementRequest();
        request.setCustomerReferenceNo(preferencesDb.getAuthUser().getPhone());
        request.setShowOrders(Boolean.FALSE.toString().toUpperCase());
        request.setShowPayments(Boolean.TRUE.toString().toUpperCase());
        request.setStatementType(EnumStatementTypes.MONTHLY.getType());

        DateHandler dateHandler = new DateHandler();
        String endDate = dateHandler.getHumanReadableDate( dateHandler.getCalendarAfterSetDays(1).getTime(), ConstStrings.DATE_FORMAT_TXN);
       // String endDate = dateHandler.getHumanReadableDate( DateHandler.dateTimeNow(), ConstStrings.DATE_FORMAT_TXN);

        String startDate = dateHandler.getDateStringBeforeSetDays(60);
        Date startDateParsed = DateHandler.getDateObjectFromDateString(startDate);
        String nuStartDate = dateHandler.getHumanReadableDate( startDateParsed, ConstStrings.DATE_FORMAT_TXN);

        request.setReceptionType(EnumStmtReceiptionTypes.MOBILE_APP.getType());
        request.setStartDate(nuStartDate);
        request.setEndDate(endDate);

        return request;

    }

    @Override
    public void serverResponseError(String error) {
        view.stopProgressDialog();
        view.displayMsgErr(error);
    }

    @Override
    public void serverResponseOrderPlacementSuccess(StatementResponse response) {

        view.stopProgressDialog();

        if(!response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
            view.displayMsgErr(response.getStatusDescription());
            return;
        }

        if(response.getReceiptionType().equalsIgnoreCase(EnumStmtReceiptionTypes.MOBILE_APP.getType())){
            getMiniStatement(response);
        }else{
            String message = "Your mini-statement has been successfully sent to your email address";
            view.displayMessageDialog(message);
        }



    }

    private void getMiniStatement(StatementResponse response) {

        /*List<StatementTxn> statementTxns = new ArrayList<>();

        for (Order order : response.getOrders()) {
            StatementTxn txn = getStatementTxnFromOrder(order);
            statementTxns.add(txn);
        }

        for (Payment payment : response.getPayments()) {
            StatementTxn txn = getStatementTxnFromPayment(payment);
            statementTxns.add(txn);
        }

        view.displayRegisteredSamples(statementTxns);*/

    }

    @NonNull
    private StatementTxn getStatementTxnFromOrder(Order order) {

        String amount = order.getAmount();
        String date = order.getCreationDate();
        String status = order.getStatus();
        String type = EnumTransactionTypes.ORDER.getType();
        StatementTxn txn = new StatementTxn();
        txn.setTransactionAmount(amount);
        txn.setTxnType(type);
        txn.setTxnDate(date);
        txn.setTxnIdOrInvoiceNo(order.getInvoiceNumber());
        txn.setStatus(status);
        return txn;

    }

    @NonNull
    private StatementTxn getStatementTxnFromPayment(Payment payment) {

        String amount = payment.getTransactionAmount();
        String date = payment.getPaymentDate();
        String status = payment.getStatus();
        String fromAccount = payment.getFromAccount();
        String type = EnumTransactionTypes.PAYMENT.getType();
        StatementTxn txn = new StatementTxn();
        txn.setTransactionAmount(amount);
        txn.setTxnType(type);
        txn.setTxnDate(date);
        txn.setTxnIdOrInvoiceNo(payment.getTransactionId());
        txn.setStatus(status);
        txn.setFromAccount(fromAccount);
        return txn;

    }


}
