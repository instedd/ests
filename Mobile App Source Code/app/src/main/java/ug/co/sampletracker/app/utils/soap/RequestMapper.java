package ug.co.sampletracker.app.utils.soap;

import org.ksoap2.serialization.SoapObject;

import ug.co.sampletracker.app.models.soap.requests.CustomerVerificationReq;
import ug.co.sampletracker.app.models.soap.responses.CustomerVerificationRes;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/4/2018.
 */

public class RequestMapper {

    private SoapSender soapSender = new SoapSender();

    public CustomerVerificationRes verifyCustomer(CustomerVerificationReq request) {

        CustomerVerificationRes resp = new CustomerVerificationRes();

        try {

            SoapObject object = soapSender.processRequest(EndPointMethods.ACTION_VERIFY_CUSTOMER, request);

            resp.setStatusCode(object.getPropertyAsString("statusCode"));
            resp.setStatusDescription(object.getPropertyAsString("statusDescription"));
            resp.setAccountName(object.getPropertyAsString("accountName"));
            resp.setAccountNumber(object.getPropertyAsString("accountNumber"));
            resp.setAccountBalance(object.getPropertyAsString("accountBalance"));
            resp.setMinimumBalance(object.getPropertyAsString("minimumBalance"));

        } catch (Exception exception) {

            resp.setStatusCode("100");
            resp.setStatusDescription("ERROR: " + exception.getMessage());

        }

        return resp;

    }


}

