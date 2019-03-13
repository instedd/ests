package ug.co.sampletracker.app.utils.constants;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/4/2018.
 */

public class EndPointMethods {

    public static final String URL = "http://192.168.137.107:3000/Service.svc";
   public static final String BASE_URL = "http://sampletracker.instedd.org/Api/";//https://41.210.174.210:8019/HimaMobileAppApi/Service.svc/";//"https://test.pegasus.co.ug:8019/HimaMobileAppApi/Service.svc/";//
    public static final String NAMESPACE = "http://tempuri.org/";
    public static final int TIME_OUT = 30000;
    public static final String BASE_SOAP_URL = "https://test.pegasus.co.ug:8019/";
    public static  String ACTION_VERIFY_CUSTOMER = "http://tempuri.org/IHimaMServicesAppService/verifyCustomer";
    public static  String METHOD_NAME = "verifyCustomer";

    /*Flexipay End Points */

    public static final String FLEXIPAY_RETURN_URL = "https://test.pegasus.co.ug:8019/TestAppApi/ReturnUrl.aspx";
    public static final String FLEXIPAY_GATEWAY_BASE_URL = "https://test.pegasus.co.ug:8019/TestPegasusPaymentsGateway/Default.aspx?";

    /* End of Flexipay End Points */

}
