package ug.co.sampletracker.app.components.receivedsamples.view;

import ug.co.sampletracker.app.connections.dataloaders.DTMiniStatement;
import ug.co.sampletracker.app.connections.dataloaders.DTSearchForOrder;
import ug.co.sampletracker.app.models.requests.SearchOrderRequest;
import ug.co.sampletracker.app.models.requests.StatementRequest;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/8/2018.
 */

public class ViewReceivedSamplesInteractorImpl implements ViewReceivedSamplesInteractor {
    @Override
    public void ordersHistory(StatementRequest request,
                              DTMiniStatement.ServerResponseMiniStatementListener responseListener) {

        DTMiniStatement dataLoader = new DTMiniStatement();
        dataLoader.setResponseListener(responseListener);
        dataLoader.miniStatement(request);

    }

    @Override
    public void searchForOrder(SearchOrderRequest request,
                               DTSearchForOrder.ServerResponseSearchForOrderListener responseListener) {

        DTSearchForOrder dataLoader = new DTSearchForOrder();
        dataLoader.setResponseListener(responseListener);
        dataLoader.searchForOrder(request);

    }
}
