package ug.co.sampletracker.app.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RegionalPrices {

    @SerializedName("regionName")
    private String regionName = "";
    @SerializedName("regionCode")
    private String regionCode = "";
    private List<RegionProdt> regionProdts = new ArrayList<>();

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public List<RegionProdt> getRegionProdts() {
        return regionProdts;
    }

    public void setRegionProdts(List<RegionProdt> regionProdts) {
        this.regionProdts = regionProdts;
    }
}
