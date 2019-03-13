package ug.co.sampletracker.app.models.responses;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.orm.dsl.Ignore;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.models.Balance;
import ug.co.sampletracker.app.models.DistributorRole;
import ug.co.sampletracker.app.models.Product;
import ug.co.sampletracker.app.models.RegionalPrices;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/3/2018.
 */

public class CustomerVerificationResponse extends Response {

    @SerializedName("accountName")
    public String accountName = "";
    @SerializedName("accountNumber")
    public String accountNumber = "";
    @SerializedName("accountBalance")
    public String accountBalance = "";
    @SerializedName("minimumBalance")
    public String minimumBalance = "";
    @SerializedName("phone")
    public String phone = "";
    @SerializedName("himaProducts")
    public List<Product> himaProducts = new ArrayList<>();
    @SerializedName("distributorRoles")
    public List<DistributorRole> distributorRoles = new ArrayList<>();
    @SerializedName("regionalPrices")
    public List<RegionalPrices> regionalPrices = new ArrayList<>();

    @Ignore
    @SerializedName("rememberMe")
    public boolean rememberMe = false;

    @SerializedName("balanceResponse")
    public Balance balanceResponse;

    public List<RegionalPrices> getRegionalPrices() {
        return regionalPrices;
    }

    public void setRegionalPrices(List<RegionalPrices> regionalPrices) {
        this.regionalPrices = regionalPrices;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(String minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public List<Product> getHimaProducts() {
        return himaProducts;
    }

    public void setHimaProducts(List<Product> himaProducts) {
        this.himaProducts = himaProducts;
    }

    public List<DistributorRole> getDistributorRoles() {
        return distributorRoles;
    }

    public void setDistributorRoles(List<DistributorRole> distributorRoles) {
        this.distributorRoles = distributorRoles;
    }

    public Balance getBalanceResponse() {
        return balanceResponse;
    }

    public void setBalanceResponse(Balance balanceResponse) {
        this.balanceResponse = balanceResponse;
    }



    public static String toGson(CustomerVerificationResponse response){

        Gson gson = new Gson();
        Type type = new TypeToken<CustomerVerificationResponse>() {}.getType();
        return gson.toJson(response, type);

    }

    public static CustomerVerificationResponse fromGson(String data){

        CustomerVerificationResponse response = new CustomerVerificationResponse();
        Gson gson = new Gson();

        Type type = new TypeToken<CustomerVerificationResponse>() {
        }.getType();
        response = gson.fromJson(data, type);

        return  response;

    }

}
