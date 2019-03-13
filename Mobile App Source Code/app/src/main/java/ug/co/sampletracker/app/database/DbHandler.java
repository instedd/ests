package ug.co.sampletracker.app.database;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.models.Balance;
import ug.co.sampletracker.app.models.Dealer;
import ug.co.sampletracker.app.models.DistributorRole;
import ug.co.sampletracker.app.models.OfficeLocation;
import ug.co.sampletracker.app.models.Packaging;
import ug.co.sampletracker.app.models.Product;
import ug.co.sampletracker.app.models.RegionalPrices;
import ug.co.sampletracker.app.models.TableRegionalPrices;
import ug.co.sampletracker.app.models.responses.CustomerVerificationResponse;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EnumDeliveryTypes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DbHandler {

    public void saveHimaRoles(List<DistributorRole> distributorRoles) {

        for (DistributorRole role : distributorRoles) {

            List<DistributorRole> rolesWithRank = DistributorRole.find(DistributorRole.class, "rank = ?", String.valueOf(role.rank));
            DistributorRole distributorRole = rolesWithRank.isEmpty() ? null : rolesWithRank.get(0);
            if(distributorRole == null){
                role.save();
                continue;
            }

            //update role
            distributorRole.groupCode = role.groupCode;
            distributorRole.itemCode = role.itemCode;
            distributorRole.itemName = role.itemName;
            distributorRole.save();

        }

    }

    public void saveHimaProducts(List<Product> himaProducts) {

        for (Product product : himaProducts) {

            List<Product> productsWithRank = Product.find(Product.class, "rank = ?", String.valueOf(product.rank));
            Product productRecord = productsWithRank.isEmpty() ? null : productsWithRank.get(0);

            if(productRecord == null){
                product.save();
                continue;
            }

            //update product
            productRecord.groupCode = product.groupCode;
            productRecord.itemCode = product.itemCode;
            productRecord.itemAmount = product.itemAmount;
            productRecord.itemName = product.itemName;
            productRecord.save();

        }

    }

    public List<Product> products(){
        return Product.listAll(Product.class);
    }

    public List<DistributorRole> distributorRoles(){
        return DistributorRole.listAll(DistributorRole.class);
    }

    public void saveCreditBalance(Balance creditBalances) {

        Balance storedBalance = Balance.first(Balance.class);
        if(storedBalance == null){
            creditBalances.save();
            return;
        }

        storedBalance.creditLimit = creditBalances.creditLimit;
        storedBalance.availableBalance = creditBalances.availableBalance;
        storedBalance.creditUsed = creditBalances.creditUsed;

    }

    public Balance creditBalances(){

        Balance storedBalance = Balance.first(Balance.class);
        if(storedBalance != null){
            return  storedBalance;
        }

        Balance balance = new Balance();
        balance.setAvailableBalance(ConstStrings.NOT_AVAILABLE);
        balance.setCreditLimit(ConstStrings.NOT_AVAILABLE);
        balance.setCreditUsed(ConstStrings.NOT_AVAILABLE);
        return  balance;

    }


    public void saveHimaPackagings(List<Packaging> packagings) {

        for (Packaging packaging : packagings) {

            List<Packaging> packagingsWithRank = Packaging.find(Packaging.class, "packaging = ?", String.valueOf(packaging.getPackaging()));
            Packaging packagingRecord = packagingsWithRank.isEmpty() ? null : packagingsWithRank.get(0);

            if(packagingRecord == null){
                packaging.save();
                continue;
            }

            //update packaging
            packagingRecord.setUnitOfMeasure(packaging.getUnitOfMeasure());
            packagingRecord.save();

        }

    }

    public String findPackageUnitsOfMeasure(String packaging) {

        List<Packaging> packagingsWithRank = Packaging.find(Packaging.class, "packaging = ?", packaging);
        return packagingsWithRank.isEmpty() ? "" : packagingsWithRank.get(0).getUnitOfMeasure();
    }

    public Product findCementTypeByName(String cementType) {

        List<Product> products = Product.find(Product.class, "item_name = ?", cementType);
        Product productRecord = products.isEmpty() ? null : products.get(0);
        return productRecord;
    }

    public void saveHimaRolesAndProducts(CustomerVerificationResponse customerVerificationRes) {

        Thread t = new Thread(() -> {

            saveHimaRoles(customerVerificationRes.getDistributorRoles());
            saveHimaProducts(customerVerificationRes.getHimaProducts());
            saveRegionalProdtPrices(customerVerificationRes.getRegionalPrices());

        });
        t.start();

    }

    public void saveDealers(List<Dealer> dealers) {

        Dealer.deleteAll(Dealer.class);
        Dealer.saveInTx(dealers);

    }

    public List<Dealer> filterDealersByLocation(String location) {

        if(location == null || location.equalsIgnoreCase("ALL") || location.isEmpty()){
            return Dealer.listAll(Dealer.class);
        }

       return Dealer.find(Dealer.class, "location = ?", location);

    }

    public List<String> getDealerLocations() {

        List<Dealer> dealers = filterDealersByLocation("ALL");
        List<String> locations = new ArrayList<>();

        locations.add("ALL");
        for (Dealer dealer : dealers) {

            if(locations.contains(dealer.getLocation()) || dealer.getLocation().equalsIgnoreCase(ConstStrings.NOT_AVAILABLE)){
                continue;
            }
            locations.add(dealer.getLocation());

        }

        return locations;

    }

    public void saveOffices(List<OfficeLocation> offices) {

        OfficeLocation.deleteAll(OfficeLocation.class);
        OfficeLocation.saveInTx(offices);

    }

    public void saveRegionalProdtPrices(List<RegionalPrices> regionalPrices) {

        List<TableRegionalPrices> tableRegionalPrices = TableRegionalPrices.getRegionalPricesData(regionalPrices);
        TableRegionalPrices.deleteAll(TableRegionalPrices.class);

        TableRegionalPrices.saveInTx(tableRegionalPrices);

    }

    public List<String> regions(){

        List<String> regions = new ArrayList<>();

        List<TableRegionalPrices> tableRegionalPrices = TableRegionalPrices.listAll(TableRegionalPrices.class);

        for (TableRegionalPrices tableRegionalPrices1 : tableRegionalPrices) {

            String regionName = tableRegionalPrices1.getRegionName();
            if(!regions.contains(regionName)){
                regions.add(regionName);
            }
        }

        return regions;

    }

    public Product findRegionProductPrice(String regionName, String productName, String customerType, boolean tobeDelivered) {

        String deliveryType = tobeDelivered ?EnumDeliveryTypes.DELIVERED.getType() : EnumDeliveryTypes.SELF_COLLECT.getType();

        String[] params = new String[]{regionName, productName,customerType,deliveryType};

        List<TableRegionalPrices> tableRegionalPrices =
                TableRegionalPrices.find(TableRegionalPrices.class, "region_name = ? " +
                        "and product_name = ? and customer_type = ? and delivery_type = ?", params);

        if(tableRegionalPrices.isEmpty() ){
            return null;
        }else{
            Product product = new Product();
            product.itemName = tableRegionalPrices.get(0).getProductName();
            product.itemAmount = tableRegionalPrices.get(0).getProductPrice();
            return product;
        }

    }

    public List<String> regionProducts(String region, String customerType) {

        List<String> products = new ArrayList<>();

        String[] params = new String[]{region, customerType};

        List<TableRegionalPrices> tableRegionalPrices = TableRegionalPrices.find(TableRegionalPrices.class, "region_name = ? and customer_type = ?", params);

        if(tableRegionalPrices.isEmpty() ){
            return products;
        }else{

            for (TableRegionalPrices data : tableRegionalPrices) {

                if(products.contains(data.getProductName())){
                    continue;
                }

                products.add(data.getProductName());
            }

            return products;
        }

    }

    public String findRegionCodeByRegionName(String regionName) {

        List<TableRegionalPrices> tableRegionalPrices = TableRegionalPrices.find(TableRegionalPrices.class, "region_name = ?", regionName);
        TableRegionalPrices data = tableRegionalPrices.isEmpty() ? null : tableRegionalPrices.get(0);
        return data == null ? "" : data.getRegionCode();

    }
}
