package ug.co.sampletracker.app.models.soap.requests;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/4/2018.
 */

public class CustomerVerificationReq implements KvmSerializable {

    public String customerReferenceNumber ;
    public String phoneNo;

    @Override
    public Object getProperty(int i) {

        Object obj = null;

        switch (i) {
            case 0:
                obj = this.customerReferenceNumber;

                break;
            case 1:
                obj = this.phoneNo;
                break;
        }
        return obj;

    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void setProperty(int i, Object value) {

        switch (i) {
            case 0:{
                customerReferenceNumber = value.toString();
                break;
            }
            case 1:{
                phoneNo = value.toString();
                break;
            }
        }

    }

    @Override
    public void getPropertyInfo(int val, Hashtable hashtable, PropertyInfo propertyInfo) {

        propertyInfo.setNamespace("http://schemas.datacontract.org/2004/07/");

        switch (val) {
            case 0:

                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "customerReferenceNumber";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "phoneNo";
                break;
        }

    }

    public String getCustomerReferenceNumber() {
        return customerReferenceNumber;
    }

    public void setCustomerReferenceNumber(String customerReferenceNumber) {
        this.customerReferenceNumber = customerReferenceNumber;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
