package ug.co.sampletracker.app.models.responses;

import java.util.ArrayList;
import java.util.List;

import ug.co.sampletracker.app.models.DataDeliveryNote;

public class DeliveryNoteInqRes extends Response {

    private List<DataDeliveryNote> deliveryNotes = new ArrayList<>();

    public List<DataDeliveryNote> getDeliveryNotes() {
        return deliveryNotes;
    }

    public void setDeliveryNotes(List<DataDeliveryNote> deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }
}
