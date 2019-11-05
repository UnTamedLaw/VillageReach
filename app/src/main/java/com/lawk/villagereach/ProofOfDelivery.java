package com.lawk.villagereach;


import com.google.gson.annotations.SerializedName;

public class ProofOfDelivery {
    @SerializedName("status")
    public String status;
    @SerializedName("shipment")
    public Stub shipment;
    @SerializedName("lineItems")
    public LineItem[] lineItems;
    @SerializedName("receivedBy")
    public String receivedBy;
    @SerializedName("deliveredBy")
    public String deliveredBy;
    @SerializedName("id")
    public String id;
}
