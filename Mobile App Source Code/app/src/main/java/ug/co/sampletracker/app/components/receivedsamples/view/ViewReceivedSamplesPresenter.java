package ug.co.sampletracker.app.components.receivedsamples.view;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import ug.co.sampletracker.app.connections.dataloaders.DTMiniStatement;
import ug.co.sampletracker.app.connections.dataloaders.DTSearchForOrder;
import ug.co.sampletracker.app.database.DataManager;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.StReceivedSample;
import ug.co.sampletracker.app.models.requests.SearchOrderRequest;
import ug.co.sampletracker.app.models.requests.StatementRequest;
import ug.co.sampletracker.app.models.responses.SearchOrderResponse;
import ug.co.sampletracker.app.models.responses.StatementResponse;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumStatementTypes;
import ug.co.sampletracker.app.utils.constants.StatusCodes;
import ug.co.sampletracker.app.utils.general.DateHandler;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/8/2018.
 */

public class ViewReceivedSamplesPresenter implements DTMiniStatement.ServerResponseMiniStatementListener, DTSearchForOrder.ServerResponseSearchForOrderListener {

    private ViewReceivedSamplesInteractor interactor;
    private ViewReceivedSamplesView view;
    private PreferencesDb preferencesDb;

    ViewReceivedSamplesPresenter(ViewReceivedSamplesView view, ViewReceivedSamplesInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
    }

    public void setPreferencesDb(PreferencesDb preferencesDb) {
        this.preferencesDb = preferencesDb;
    }

    void loadReceivedSamples() {

        /*StatementRequest request = getStatementRequest();
        view.showProgressDialog();
        interactor.ordersHistory(request, this);*/

        view.showProgressDialog();
        List<StReceivedSample> receivedSamples = DataManager.appData.getReceivedSamples();
        view.stopProgressDialog();

        view.displayReceivedSamples(receivedSamples);

    }

    @NonNull
    private StatementRequest getStatementRequest() {

        StatementRequest request = new StatementRequest();
        request.setCustomerReferenceNo(preferencesDb.getAuthDealer().getReference());
        request.setShowOrders(Boolean.TRUE.toString().toUpperCase());
        request.setShowPayments(Boolean.FALSE.toString().toUpperCase());
        request.setStatementType(EnumStatementTypes.MONTHLY.getType());

        DateHandler dateHandler = new DateHandler();

        String endDate = dateHandler.getHumanReadableDate( dateHandler.getCalendarAfterSetDays(1).getTime(), ConstStrings.DATE_FORMAT_TXN);
    //    String endDate = dateHandler.getHumanReadableDate( DateHandler.dateTimeNow(), ConstStrings.DATE_FORMAT_TXN);

        String startDate = dateHandler.getDateStringBeforeSetDays(300);
        Date startDateObj = DateHandler.getDateObjectFromDateString(startDate);

        String nuStartDate = dateHandler.getHumanReadableDate(startDateObj,ConstStrings.DATE_FORMAT_TXN);

        request.setStartDate(nuStartDate);
        request.setEndDate(endDate);
        return request;

    }

    @Override
    public void serverResponseError(String error) {
        view.stopProgressDialog();
        view.displayMessage(error);
    }

    @Override
    public void serverResponseSearchForOrderSuccess(SearchOrderResponse response) {

        view.stopProgressDialog();
        if(!response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
            view.displayMessage(response.getStatusDescription());
            return;
        }

//        view.displayReceivedSamples(response.getOrders());

    }

    @Override
    public void serverResponseOrderPlacementSuccess(StatementResponse response) {

        view.stopProgressDialog();
        if(!response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
            view.displayMessage(response.getStatusDescription());
            return;
        }
  //      view.displayReceivedSamples(response.getOrders());

    }

    void searchForOrder(String searchValue) {

        if(searchValue == null || searchValue.trim().isEmpty()){
            return;
        }

        view.showProgressDialog();

        SearchOrderRequest request = new SearchOrderRequest();
        request.setSearchValue(searchValue);
        request.setFilterBy(ConstStrings.ORDER_SEARCH_FILTER_INVOICE);
        interactor.searchForOrder(request, this);

    }
}
