package ug.co.sampletracker.app.models.responses;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.models.Dealer;
import ug.co.sampletracker.app.models.DistributorRole;
import ug.co.sampletracker.app.models.OfficeLocation;
import ug.co.sampletracker.app.models.Packaging;
import ug.co.sampletracker.app.models.Product;
import ug.co.sampletracker.app.models.RegionalPrices;
import ug.co.sampletracker.app.models.StNotification;
import ug.co.sampletracker.app.models.StRegisteredSamplePojo;
import ug.co.sampletracker.app.models.StDestination;
import ug.co.sampletracker.app.models.StDisease;
import ug.co.sampletracker.app.models.StFacility;
import ug.co.sampletracker.app.models.StReceivedSample;
import ug.co.sampletracker.app.models.StSpecimen;
import ug.co.sampletracker.app.models.StTransporter;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/10/2018.
 */

public class AppDataResponse extends Response {


    private List<StDisease> diseases = new ArrayList<>();
    private List<StTransporter> transporter = new ArrayList<>();
    private List<StDestination> destination = new ArrayList<>();
    private List<StFacility> health_facilities = new ArrayList<>();
    private List<StSpecimen> specimen = new ArrayList<>();
    private List<StReceivedSample> receivedSamples = new ArrayList<>();
    private List<StRegisteredSamplePojo> registeredSamples = new ArrayList<>();
    private List<StNotification> notifications = new ArrayList<>();


    private List<DistributorRole> distributorRoles = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Packaging> packagings  = new ArrayList<>();
    private List<Dealer> dealers = new ArrayList<>();
    private List<OfficeLocation> offices = new ArrayList<>();
    private List<RegionalPrices> regionalPrices = new ArrayList<>();


    public List<StNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<StNotification> notifications) {
        this.notifications = notifications;
    }

    public List<StRegisteredSamplePojo> getRegisteredSamples() {
        return registeredSamples;
    }

    public void setRegisteredSamples(List<StRegisteredSamplePojo> registeredSamples) {
        this.registeredSamples = registeredSamples;
    }

    public List<StReceivedSample> getReceivedSamples() {
        return receivedSamples;
    }

    public void setReceivedSamples(List<StReceivedSample> receivedSamples) {
        this.receivedSamples = receivedSamples;
    }

    public List<StDisease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<StDisease> diseases) {
        this.diseases = diseases;
    }

    public List<StTransporter> getTransporter() {
        return transporter;
    }

    public void setTransporter(List<StTransporter> transporter) {
        this.transporter = transporter;
    }

    public List<StDestination> getDestination() {
        return destination;
    }

    public void setDestination(List<StDestination> destination) {
        this.destination = destination;
    }

    public List<StFacility> getHealth_facilities() {
        return health_facilities;
    }

    public void setHealth_facilities(List<StFacility> health_facilities) {
        this.health_facilities = health_facilities;
    }

    public List<StSpecimen> getSpecimen() {
        return specimen;
    }

    public void setSpecimen(List<StSpecimen> specimen) {
        this.specimen = specimen;
    }

    public List<RegionalPrices> getRegionalPrices() {
        return regionalPrices;
    }

    public void setRegionalPrices(List<RegionalPrices> regionalPrices) {
        this.regionalPrices = regionalPrices;
    }

    public List<DistributorRole> getDistributorRoles() {
        return distributorRoles;
    }

    public void setDistributorRoles(List<DistributorRole> distributorRoles) {
        this.distributorRoles = distributorRoles;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Packaging> getPackagings() {
        return packagings;
    }

    public void setPackagings(List<Packaging> packagings) {
        this.packagings = packagings;
    }

    public List<Dealer> getDealers() {
        return dealers;
    }

    public void setDealers(List<Dealer> dealers) {
        this.dealers = dealers;
    }

    public List<OfficeLocation> getOffices() {
        return offices;
    }

    public void setOffices(List<OfficeLocation> offices) {
        this.offices = offices;
    }
}
