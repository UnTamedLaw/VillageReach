package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;

public class LineItem {
    @SerializedName("id")
    public String id;
    @SerializedName("orderable")
    public Stub orderable;
    @SerializedName("quantityShipped")
    public int quantityShipped;
    @SerializedName("quantityAccepted")
    public int quantityAccepted;
    @SerializedName("quantityRejected")
    public int quantityRejected;
    @SerializedName("rejectionReasonId")
    public String rejectionReasonId;
    @SerializedName("notes")
    public String notes;

}
