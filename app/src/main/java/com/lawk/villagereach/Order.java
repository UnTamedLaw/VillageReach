package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("externalId")
    public String externalId;
    @SerializedName("emergency")
    public Boolean emergency;
    @SerializedName("requestingFacility")
    public Facility requestingFacility;
    @SerializedName("receivingFacility")
    public Facility receivingFacility;
    @SerializedName("supplyingFacility")
    public Facility supplyingFacility;
    @SerializedName("orderCode")
    public String orderCode;
    @SerializedName("status")
    public String status;
    public String id;
}

