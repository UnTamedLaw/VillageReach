package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;

public class ProcessingPeriod {
    @SerializedName("description")
    public String description;
    @SerializedName("endDate")
    public String endDate;
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("processingSchedule")
    public ProcessingPeriod processingSchedule;
    @SerializedName("startDate")
    public String startDate;
}
