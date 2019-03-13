package ug.co.sampletracker.app.components.receivedsamples.view;

import java.util.List;

import ug.co.sampletracker.app.models.StReceivedSample;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/8/2018.
 */

public interface ViewReceivedSamplesView {
    void showProgressDialog();
    void stopProgressDialog();

    void displayMessage(String error);

    void displayReceivedSamples(List<StReceivedSample> orders);

}
