package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;

public class Program {
    @SerializedName("active")
    public Boolean active;
    @SerializedName("code")
    public String code;
    @SerializedName("description")
    public String description;
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("periodsSkippable")
    public Boolean periodsSkippable;
    @SerializedName("showNonFullSupplyTab")
    public Boolean showNonFullSupplyTab;
    @SerializedName("supportLocallyFulfilled")
    public Boolean supportLocallyFulfilled;

    public String getName(){
        return this.name;
    }
}

