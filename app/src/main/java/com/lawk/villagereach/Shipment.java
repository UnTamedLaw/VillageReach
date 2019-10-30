package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;

public class Shipment {
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

    public static class Order {
        @SerializedName("id")
        public String id;
        @SerializedName("emergency")
        public Boolean emergency;
    }
}
