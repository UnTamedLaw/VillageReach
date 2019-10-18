package com.lawk.villagereach;

import java.util.Date;

public class Order {

    //order id
    private String id;
    private String createdbyid;
    private Date createddate;
    private Boolean emergency;
    private String externalid;
    //requesting facility
    private String facilityid;
    private String ordercode;
    private String processingperiodid;
    private String programid;
    private Float quotedcost;
    private String receivingfacilityid;
    private String requestingfacilityid;
    private String status;
    private String supplyingfacilityid;
    private Date lastupdatedate;
    private String lastupdaterid;

    public Order(String id,
                 String createdbyid,
                 Date createddate,
                 Boolean emergency,
                 String externalid,
                 String facilityid,
                 String ordercode,
                 String processingperiodid,
                 String programid,
                 Float quotedcost,
                 String receivingfacilityid,
                 String requestingfacilityid,
                 String status,
                 String supplyingfacilityid,
                 Date lastupdatedate,
                 String lastupdaterid){
        this.id = id;
        this.createdbyid = createdbyid;
        this.createddate = createddate;
        this.emergency = emergency;
        this.externalid = externalid;
        this.facilityid = facilityid;
        this.ordercode = ordercode;
        this.processingperiodid = processingperiodid;
        this.programid = programid;
        this.quotedcost = quotedcost;
        this.receivingfacilityid = receivingfacilityid;
        this.requestingfacilityid = requestingfacilityid;
        this.status = status;
        this.supplyingfacilityid = supplyingfacilityid;
        this.lastupdatedate = lastupdatedate;
        this.lastupdaterid = lastupdaterid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedbyid() {
        return createdbyid;
    }

    public void setCreatedbyid(String createdbyid) {
        this.createdbyid = createdbyid;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Boolean getEmergency() {
        return emergency;
    }

    public void setEmergency(Boolean emergency) {
        this.emergency = emergency;
    }

    public String getExternalid() {
        return externalid;
    }

    public void setExternalid(String externalid) {
        this.externalid = externalid;
    }

    public String getFacilityid() {
        return facilityid;
    }

    public void setFacilityid(String facilityid) {
        this.facilityid = facilityid;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getProcessingperiodid() {
        return processingperiodid;
    }

    public void setProcessingperiodid(String processingperiodid) {
        this.processingperiodid = processingperiodid;
    }

    public String getProgramid() {
        return programid;
    }

    public void setProgramid(String programid) {
        this.programid = programid;
    }

    public Float getQuotedcost() {
        return quotedcost;
    }

    public void setQuotedcost(Float quotedcost) {
        this.quotedcost = quotedcost;
    }

    public String getReceivingfacilityid() {
        return receivingfacilityid;
    }

    public void setReceivingfacilityid(String receivingfacilityid) {
        this.receivingfacilityid = receivingfacilityid;
    }

    public String getRequestingfacilityid() {
        return requestingfacilityid;
    }

    public void setRequestingfacilityid(String requestingfacilityid) {
        this.requestingfacilityid = requestingfacilityid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupplyingfacilityid() {
        return supplyingfacilityid;
    }

    public void setSupplyingfacilityid(String supplyingfacilityid) {
        this.supplyingfacilityid = supplyingfacilityid;
    }

    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    public String getLastupdaterid() {
        return lastupdaterid;
    }

    public void setLastupdaterid(String lastupdaterid) {
        this.lastupdaterid = lastupdaterid;
    }
}
