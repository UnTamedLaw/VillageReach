package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;

public class Shipment {
    @SerializedName("order")
    public Order order;
    @SerializedName("shippedBy")
    public Stub shippedBy;
    @SerializedName("receivedDate")
    public String receivedDate;
    @SerializedName("lineItems")
    public LineItem[] lineItems;
    @SerializedName("id")
    public String id;

}
