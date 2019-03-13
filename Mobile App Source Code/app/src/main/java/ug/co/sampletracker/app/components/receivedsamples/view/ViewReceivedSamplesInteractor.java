package ug.co.sampletracker.app.components.receivedsamples.view;

import ug.co.sampletracker.app.connections.dataloaders.DTMiniStatement;
import ug.co.sampletracker.app.connections.dataloaders.DTSearchForOrder;
import ug.co.sampletracker.app.models.requests.SearchOrderRequest;
import ug.co.sampletracker.app.models.requests.StatementRequest;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/8/2018.
 */

public interface ViewReceivedSamplesInteractor {
    void ordersHistory(StatementRequest request, DTMiniStatement.ServerResponseMiniStatementListener responseListener);
    void searchForOrder(SearchOrderRequest request, DTSearchForOrder.ServerResponseSearchForOrderListener responseListerner);
}
