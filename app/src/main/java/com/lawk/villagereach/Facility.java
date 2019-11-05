package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;

public class Facility {
    @SerializedName("active")
    public Boolean active;
    @SerializedName("code")
    public String code;
    @SerializedName("comment")
    public String comment;
    @SerializedName("description")
    public String description;
    @SerializedName("enabled")
    public Boolean enabled;
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
}
