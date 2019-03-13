package ug.co.sampletracker.app.components.registeredsamples.view;

import ug.co.sampletracker.app.connections.dataloaders.DTMiniStatement;
import ug.co.sampletracker.app.models.requests.StatementRequest;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/28/2018.
 */

public interface ViewRegisteredSamplesInteractor {

    void miniStatement(StatementRequest request, DTMiniStatement.ServerResponseMiniStatementListener responseListener);
}
