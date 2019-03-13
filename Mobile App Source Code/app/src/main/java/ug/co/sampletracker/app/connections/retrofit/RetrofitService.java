
package ug.co.sampletracker.app.connections.retrofit;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import ug.co.sampletracker.app.models.Balance;
import ug.co.sampletracker.app.models.StDestination;
import ug.co.sampletracker.app.models.StDisease;
import ug.co.sampletracker.app.models.StFacility;
import ug.co.sampletracker.app.models.StReceivedSample;
import ug.co.sampletracker.app.models.StSpecimen;
import ug.co.sampletracker.app.models.StTransporter;
import ug.co.sampletracker.app.models.requests.AppDataRequest;
import ug.co.sampletracker.app.models.requests.BalanceRequest;
import ug.co.sampletracker.app.models.requests.CustomerVerificationRequest;
import ug.co.sampletracker.app.models.requests.GetTransactionStatusRequest;
import ug.co.sampletracker.app.models.requests.OrderUnrefRequest;
import ug.co.sampletracker.app.models.requests.RegistrationRequest;
import ug.co.sampletracker.app.models.requests.RequestPaymentHistoryOTPRequest;
import ug.co.sampletracker.app.models.requests.RequestResetPasswordOTPReq;
import ug.co.sampletracker.app.models.requests.ResetPasswordReq;
import ug.co.sampletracker.app.models.requests.SampleReceiptionReq;
import ug.co.sampletracker.app.models.requests.SearchOrderRequest;
import ug.co.sampletracker.app.models.requests.StatementRequest;
import ug.co.sampletracker.app.models.requests.VerifyPaymentHistoryOTPRequest;
import ug.co.sampletracker.app.models.responses.AppDataResponse;
import ug.co.sampletracker.app.models.responses.CustomerVerificationResponse;
import ug.co.sampletracker.app.models.responses.GetTransactionStatusResponse;
import ug.co.sampletracker.app.models.responses.LoginStResponse;
import ug.co.sampletracker.app.models.responses.PaymentUnreferencedResponse;
import ug.co.sampletracker.app.models.responses.ReceiveSampleRes;
import ug.co.sampletracker.app.models.responses.RegisterSampleRes;
import ug.co.sampletracker.app.models.responses.RegistrationResponse;
import ug.co.sampletracker.app.models.responses.RequestPaymentHistoryOTPResponse;
import ug.co.sampletracker.app.models.responses.RequestResetPasswordOTPRes;
import ug.co.sampletracker.app.models.responses.ResetPasswordRes;
import ug.co.sampletracker.app.models.responses.SearchOrderResponse;
import ug.co.sampletracker.app.models.responses.StatementResponse;
import ug.co.sampletracker.app.models.responses.VerifyPaymentHistoryOTPResponse;

/**
 * Created by timothy kasaga on 2/27/2017.
 */


public interface RetrofitService {

    @POST("verifyCustomer")
    Call<CustomerVerificationResponse> verifyCustomer(@Body CustomerVerificationRequest request);
    @POST("getCustomerBalances")
    Call<Balance> balanceInquiry(@Body BalanceRequest request);
    @POST("getMinistatement")
    Call<StatementResponse> ministatement(@Body StatementRequest request);
    @POST("getAppData")
    Call<AppDataResponse> getAppData(@Body AppDataRequest request);
    @POST("payHimaUnreferenced")
    Call<PaymentUnreferencedResponse> payHimaUnreferenced(@Body OrderUnrefRequest request);
    @POST("searchForOrder")
    Call<SearchOrderResponse> searchForOrder(@Body SearchOrderRequest request);
    @POST("requestPaymentHistoryOTP")
    Call<RequestPaymentHistoryOTPResponse> requestOTP(@Body RequestPaymentHistoryOTPRequest request);
    @POST("verifyPaymentHistoryOTP")
    Call<VerifyPaymentHistoryOTPResponse> verifyOTP(@Body VerifyPaymentHistoryOTPRequest request);
    @POST("getTransactionStatus")
    Call<GetTransactionStatusResponse> getTransactionStatus(@Body GetTransactionStatusRequest request);
    @POST("loginUser")
    Call<RegistrationResponse> registerUser(@Body RegistrationRequest request);
    @POST("updateUser")
    Call<RegistrationResponse> updateUser(@Body RegistrationRequest request);
    @POST("updateUserPassword")
    Call<RegistrationResponse> updateUserPassword(@Body RegistrationRequest request);
    @POST("requestResetPasswordOTP")
    Call<RequestResetPasswordOTPRes> requestResetPasswordOTP(@Body RequestResetPasswordOTPReq request);
    @POST("resetPassword")
    Call<ResetPasswordRes> resetPassword(@Body ResetPasswordReq request);

    /* ST End Points */
    @GET("api/disease")
    Call<List<StDisease>> getDiseases();
    @GET("api/specimen")
    Call<List<StSpecimen>> getSpecimenTypes();
    @GET("api/destination")
    Call<List<StDestination>> getDestination();
    @GET("api/transporter")
    Call<List<StTransporter>> getTransporters();
    @GET("api/healthFacility")
    Call<List<StFacility>> getHealthFacilities();
    @GET("api/receivedSamples")
    Call<List<StReceivedSample>> getReceivedSamples();

    @FormUrlEncoded
    @POST("login")
    Call<LoginStResponse> login(@Field("txt_username") String username, @Field("txt_password") String password);

    @GET("appData")
    Call<AppDataResponse> getSampleTrackerAppData();

    @Multipart
    @POST("registerSample")
    Call<RegisterSampleRes> registerSample(
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part uploadFileuploadFile);

    @POST("receiveSample")
    Call<ReceiveSampleRes> submitReceivedSample(@Body SampleReceiptionReq request);

    @FormUrlEncoded
    @POST("receiveSample")
    Call<ReceiveSampleRes> submitReceivedSample(
            @Field("final_destination") int final_destination,
            @Field("sample_status") String sample_status,
            @Field("transporter") int transporter,
            @Field("barcode") String barcode,
            @Field("facility_code") int facility_code,
            @Field("is_destination") String is_destination,
            @Field("dateReceived") String dateReceived);


}

