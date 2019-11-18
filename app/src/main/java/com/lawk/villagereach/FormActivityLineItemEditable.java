package com.lawk.villagereach;

public class FormActivityLineItemEditable {
    public int quantityAccepted;
    public int quantityRejected;
    public String rejectionReason;
    public String notes;

    public FormActivityLineItemEditable(int quantityAccepted, int quantityRejected, String rejectionReason, String notes) {
        this.quantityAccepted = quantityAccepted;
        this.quantityRejected = quantityRejected;
        this.rejectionReason = rejectionReason;
        this.notes = notes;
    }
    public void setQuantityAccepted(int quantityAccepted) {
        this.quantityAccepted = quantityAccepted;
    }

    public void setQuantityRejected(int quantityRejected) {
        this.quantityRejected = quantityRejected;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}