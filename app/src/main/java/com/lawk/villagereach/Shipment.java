package com.lawk.villagereach;

import com.google.gson.annotations.SerializedName;

public class Shipment {
    public Stub order;
    public String receivedDate;
    public LineItem[] lineItems;

    public String id;
}
