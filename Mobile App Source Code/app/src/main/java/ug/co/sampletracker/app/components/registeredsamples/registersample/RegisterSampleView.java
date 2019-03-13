package ug.co.sampletracker.app.components.registeredsamples.registersample;

import java.util.List;

import ug.co.sampletracker.app.models.StDestination;
import ug.co.sampletracker.app.models.StDisease;
import ug.co.sampletracker.app.models.StFacility;
import ug.co.sampletracker.app.models.StSpecimen;
import ug.co.sampletracker.app.models.StTransporter;
import ug.co.sampletracker.app.models.requests.OrderUnrefRequest;
import ug.co.sampletracker.app.models.responses.PaymentUnreferencedResponse;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/10/2018.
 */

public interface RegisterSampleView {
    void displayMessageFailedToLoadAppData(String error);
    void displayDistributorRoles(List<String> distributorRoles);

    void stopProgressDialog();
    void startProgressDialog(String message);

    void displayPackagings(List<String> packagings);
    void displayPackagingsUnits(List<String> packagingUnits);

    void displayCementTypes(List<String> cementTypes);

    void displayValidationErrors(List<String> invalidFields);

    void displayMessageDialog(String message);

    void displayPaymentSuccessfulMessage(PaymentUnreferencedResponse response);

    void setUnitOfMeasureSelection(String unitOfMeasure);

    void displayTotalAmount(String amount);

    void confirmPayment(OrderUnrefRequest request);

    void openPaymentsHistoryPage();

    void enabledVerifyOTPFields(boolean enabled);

    void displaySuccessfullPaymentsAndStartWaitingDialog(PaymentUnreferencedResponse response);
    void displayRegions(List<String> regions);
    void displayDiseases(List<StDisease> diseases);
    void displayDestinations(List<StDestination> destinations);
    void displayHealthFacilities(List<StFacility> health_facilities);
    void displaySpecimen(List<StSpecimen> specimen);
    void displayTransporters(List<StTransporter> transporters);

    void clearFields();

}
