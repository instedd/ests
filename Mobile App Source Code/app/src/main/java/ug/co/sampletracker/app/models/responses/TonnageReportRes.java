package ug.co.sampletracker.app.models.responses;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.models.DataTonnage;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/24/2018.
 */
public class TonnageReportRes extends Response {

    List<DataTonnage> data = new ArrayList<>();

    public List<DataTonnage> getData() {
        return data;
    }

    public void setData(List<DataTonnage> data) {
        this.data = data;
    }
}
