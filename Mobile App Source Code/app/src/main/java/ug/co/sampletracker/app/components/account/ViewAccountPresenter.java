package ug.co.sampletracker.app.components.account;

import ug.co.sampletracker.app.connections.dataloaders.DTBalanceInquiry;
import ug.co.sampletracker.app.connections.dataloaders.DTUpdateUserInfo;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.database.PreferencesDb;
import ug.co.sampletracker.app.models.Balance;
import ug.co.sampletracker.app.models.auth.AuthUser;
import ug.co.sampletracker.app.models.requests.BalanceRequest;
import ug.co.sampletracker.app.models.requests.RegistrationRequest;
import ug.co.sampletracker.app.models.responses.LoginResponse;
import ug.co.sampletracker.app.models.responses.RegistrationResponse;
import ug.co.sampletracker.app.utils.constants.StatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/7/2018.
 */

public class ViewAccountPresenter implements DTBalanceInquiry.ServerResponseBalanceInquiryListener, DTUpdateUserInfo.ServerResponseUpdateUserInfoListener {

    private ViewAccountView view ;
    private ViewAccountInteractor interactor;
    private DbHandler dbHandler;
    private PreferencesDb preferencesDb;

    ViewAccountPresenter(ViewAccountView view, ViewAccountInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    void setDbHandler(DbHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    void setPreferencesDb(PreferencesDb preferencesDb) {
        this.preferencesDb = preferencesDb;
    }

    void loadCreditBalances() {

        view.startDialog("Loading Balances");

        BalanceRequest balanceRequest = new BalanceRequest();
        balanceRequest.setCustomerReferenceNo(preferencesDb.customerReferenceNumber());
        interactor.loadCreditBalancesFromServer(dbHandler,balanceRequest, this);

    }

    @Override
    public void serverResponseError(String error) {
        view.stopDialog();
        view.displayMessage(error);
    }

    @Override
    public void serverResponseBalanceInquirySuccess(Balance balance) {

        view.stopDialog();
        view.displayCreditBalances(balance);

        if(balance.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
            interactor.saveCreditBalance(dbHandler,balance);
        }

    }

    public LoginResponse getLoginResponse() {
        //todo get Data from SharedPreferences
        LoginResponse resp = new LoginResponse();
        resp.setEmail(preferencesDb.getCustomerEmail());
        resp.setName("Timothy Kasaga");
        resp.setPhone(preferencesDb.getCustomerPhone());
        return resp;
    }

    public void updateGeneralAccountInfo(RegistrationRequest request) {

        view.startDialog("Updating Information");
        DTUpdateUserInfo dtUpdateUserInfo = new DTUpdateUserInfo();
        dtUpdateUserInfo.setResponseListener(this);
        dtUpdateUserInfo.updateUser(request);

    }

    @Override
    public void serverResponseUpdateUserInfoError(String error) {

        view.stopDialog();
        view.displayMessage(error);

    }

    @Override
    public void serverResponseUpdateUserInfoSuccess(RegistrationResponse response) {

       view.stopDialog();

        if(!response.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){
            view.displayMessage(response.getStatusDescription());
            return;
        }

        view.displayMessage(response.getStatusDescription());
        //todo update preferences info
        AuthUser user = preferencesDb.getAuthUser();
        user.setName(response.getName());
        user.setEmail(response.getEmail());

        preferencesDb.setAuthUser(user);

    }
}
