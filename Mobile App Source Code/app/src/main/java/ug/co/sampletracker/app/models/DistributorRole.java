package ug.co.sampletracker.app.models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DistributorRole extends SugarRecord{

    @SerializedName("rank")
    public int rank;
    @SerializedName("groupCode")
    public String groupCode = "";
    @SerializedName("itemCode")
    public String itemCode = "";
    @SerializedName("itemName")
    public String itemName = "";

    public DistributorRole() {
    }

}
