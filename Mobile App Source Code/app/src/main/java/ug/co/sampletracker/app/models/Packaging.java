package ug.co.sampletracker.app.models;

import com.orm.SugarRecord;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/10/2018.
 */

public class Packaging extends SugarRecord {

    private String packaging;
    private String unitOfMeasure;

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

}
