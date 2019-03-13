package ug.co.sampletracker.app.components.registeredsamples.view;

import java.util.List;

import ug.co.sampletracker.app.models.StRegisteredSamplePojo;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 4/28/2018.
 */

public interface ViewRegisteredSamplesView {

    void displayRegisteredSamples(List<StRegisteredSamplePojo> statementTxns);

    void displayMsgErr(String message);

    void stopProgressDialog();

    void startProgressDialog();

    void displayMessageDialog(String message);
}
