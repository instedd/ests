package ug.co.sampletracker.app.utils.interfaces;

import java.util.HashMap;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 7/3/2018.
 */

public interface CustomView {

    void displayValidationErrors(HashMap<String, String> invalidFields);
    void showDialog(String title);
    void closeDialog();
    void displayError(String error);
    void displayMessage(String message);

}
