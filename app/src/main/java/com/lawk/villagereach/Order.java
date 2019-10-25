package com.lawk.villagereach;



public class Order {

    private String id;
    private String ordercode;     //gives order number
    private String status;
    private String requestingFacility;
    private String supplyingFacility;
    private String program;
    private String processingPeriod;
    private String createdDate;
    private Boolean emergency;
    private String id;

    public Order(String id, String ordercode, String status, String requestingFacility, String supplyingFacility, String program, String processingPeriod, String createdDate, Boolean emergency) {
        this.id = id;
        this.ordercode = ordercode;
        this.status = status;
        this.requestingFacility = requestingFacility;
        this.supplyingFacility = supplyingFacility;
        this.program = program;
        this.processingPeriod = processingPeriod;
        this.createdDate = createdDate;
        this.emergency = emergency;
        this.id = id;
    }
    public String getId() { return id;}
    public String toString() {
        return getClass().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestingFacility() {
        return requestingFacility;
    }

    public void setRequestingFacility(String requestingFacility) {
        this.requestingFacility = requestingFacility;
    }

    public String getSupplyingFacility() {
        return supplyingFacility;
    }

    public void setSupplyingFacility(String supplyingFacility) {
        this.supplyingFacility = supplyingFacility;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getProcessingPeriod() {
        return processingPeriod;
    }

    public void setProcessingPeriod(String processingPeriod) {
        this.processingPeriod = processingPeriod;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getEmergency() {
        return emergency;
    }

    public void setEmergency(Boolean emergency) {
        this.emergency = emergency;
    }


    }

