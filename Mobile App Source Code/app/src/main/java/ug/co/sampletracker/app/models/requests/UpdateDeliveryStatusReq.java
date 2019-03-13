package ug.co.sampletracker.app.models.requests;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 10/23/2018.
 */
public class UpdateDeliveryStatusReq {

    private String customerReference;
    private String deliveryNoteNo;
    private String remark;
    private String isRemarkComplaint;
    private String orderNo;
    private String wasDelivered;

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public String getDeliveryNoteNo() {
        return deliveryNoteNo;
    }

    public void setDeliveryNoteNo(String deliveryNoteNo) {
        this.deliveryNoteNo = deliveryNoteNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsRemarkComplaint() {
        return isRemarkComplaint;
    }

    public void setIsRemarkComplaint(String isRemarkComplaint) {
        this.isRemarkComplaint = isRemarkComplaint;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getWasDelivered() {
        return wasDelivered;
    }

    public void setWasDelivered(String wasDelivered) {
        this.wasDelivered = wasDelivered;
    }
}
