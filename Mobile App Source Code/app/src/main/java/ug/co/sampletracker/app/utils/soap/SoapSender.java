package ug.co.sampletracker.app.utils.soap;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import ug.co.sampletracker.app.models.soap.requests.CustomerVerificationReq;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;


/**
 * Created by Jackie on 17-Oct-17.
 */

public class SoapSender {


    public SoapObject processRequest(String method, CustomerVerificationReq request) throws SocketTimeoutException, UnknownHostException, Exception {

        SoapObject soapRequest = new SoapObject(EndPointMethods.NAMESPACE, EndPointMethods.METHOD_NAME);

        PropertyInfo pi = new PropertyInfo();
        pi.setName("verificationRequest");
        pi.setValue(request);
        pi.setType(CustomerVerificationReq.class);

        soapRequest.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.setOutputSoapObject(soapRequest);
        envelope.addMapping(EndPointMethods.NAMESPACE, "verificationRequest", CustomerVerificationReq.class);

        HttpTransportSE transportSE = new HttpTransportSE(EndPointMethods.URL, EndPointMethods.TIME_OUT);

        transportSE.debug = true;
        transportSE.call(EndPointMethods.ACTION_VERIFY_CUSTOMER, envelope);

        Log.e("SOAP REQUEST", transportSE.requestDump);
        Log.e("SOAP RESPONSE", transportSE.responseDump);

        SoapObject soapResponse = (SoapObject) envelope.getResponse();
        return soapResponse;

    }


}
