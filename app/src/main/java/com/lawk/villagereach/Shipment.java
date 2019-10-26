package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;

public class Shipment {
    @SerializedName("deliveredBy")
    public String deliveredBy;
    @SerializedName("id")
    public String id;
    @SerializedName("lineItems")
    public Stub[] lineItems;
    @SerializedName("receivedBy")
    public String receivedBy;
    @SerializedName("receivedDate")
    public String receivedDate;
    @SerializedName("shipment")
    public Stub shipment;

}
