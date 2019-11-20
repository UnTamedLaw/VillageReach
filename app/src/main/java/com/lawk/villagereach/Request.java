package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;

public class Request {
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
    @SerializedName("receivedDate")
    public String receivedDate;
}
