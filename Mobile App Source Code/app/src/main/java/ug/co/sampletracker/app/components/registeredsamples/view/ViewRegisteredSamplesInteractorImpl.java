package ug.co.sampletracker.app.components.registeredsamples.view;

import ug.co.sampletracker.app.connections.dataloaders.DTMiniStatement;
import ug.co.sampletracker.app.models.requests.StatementRequest;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/28/2018.
 */

public class ViewRegisteredSamplesInteractorImpl implements ViewRegisteredSamplesInteractor {
    @Override
    public void miniStatement(StatementRequest request, DTMiniStatement.ServerResponseMiniStatementListener responseListener) {

        DTMiniStatement dataLoader = new DTMiniStatement();
        dataLoader.setResponseListener(responseListener);
        dataLoader.miniStatement(request);

    }
}
