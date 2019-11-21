package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;
import java.util.Date;


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
    @SerializedName("id")
    public String id;
    @SerializedName("program")
    public Program program;
    @SerializedName("processingPeriod")
    public ProcessingPeriod processingPeriod;
    @SerializedName("createdDate")
    public String createdDate;
    @SerializedName("orderLineItems")
    public LineItem[] orderLineItems;

}

