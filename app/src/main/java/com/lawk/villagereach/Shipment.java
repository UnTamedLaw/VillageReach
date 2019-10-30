package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;

public class Shipment {
    @SerializedName("order")
    public Orderable order;
//  @SerializedName("lineItems");
    @SerializedName("status")
    public String status;
    @SerializedName("receivedBy")
    public String receivedBy;
    @SerializedName("deliveredBy")
    public String deliveredBy;
    @SerializedName("receivedDate")
    public String receivedDate;
    @SerializedName("id")
    public String id;

}
