package ug.co.sampletracker.app.components.account;

import ug.co.sampletracker.app.connections.dataloaders.DTBalanceInquiry;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.models.Balance;
import ug.co.sampletracker.app.models.requests.BalanceRequest;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/7/2018.
 */

public interface ViewAccountInteractor {

    void loadCreditBalancesFromServer(DbHandler dbHandler, BalanceRequest request,
                                      DTBalanceInquiry.ServerResponseBalanceInquiryListener listener);

    Balance loadCreditBalancesFromServer(DbHandler dbHandler);
    void saveCreditBalance(DbHandler dbHandler, Balance balance);

}
