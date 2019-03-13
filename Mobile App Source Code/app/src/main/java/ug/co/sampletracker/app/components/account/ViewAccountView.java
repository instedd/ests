package ug.co.sampletracker.app.components.account;

import ug.co.sampletracker.app.models.Balance;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/7/2018.
 */

public interface ViewAccountView {
    void displayCreditBalances(Balance balance);
    void displayMessage(String error);
    void startDialog(String s);
    void stopDialog();
}
