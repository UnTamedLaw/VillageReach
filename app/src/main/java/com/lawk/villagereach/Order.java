package com.lawk.villagereach;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

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
    }
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

    public static class Builder {
        Order order;

        public Builder() {
            order = new Order("", "", "", "", "", "", "", "", false);
        }

        public  Builder id(String id) {
            order.id = id;
            return this;
        }

        public Builder orderCode(String orderCode) {
            order.ordercode = orderCode;
            return this;
        }

        public Builder status(String status) {
            order.status = status;
            return this;
        }

        public Builder requestingFacility(String requestingFacility) {
            order.requestingFacility = requestingFacility;
            return this;
        }

        public Builder supplyingFacility(String supplyingFacility) {
            order.supplyingFacility = supplyingFacility;
            return this;
        }

        public Builder program(String program) {
            order.program = program;
            return this;
        }

        public Builder processingPeriod(String proccessingPeriod) {
            order.processingPeriod = proccessingPeriod;
            return this;
        }

        public Builder createdDate(String createdDate) {
            order.createdDate = createdDate;
            return this;
        }

        public Builder emergency(boolean emergency) {
            order.emergency = emergency;
            return this;
        }

        public Order build(){
            return order;
        }
    }

    public static Order[] initOrder(){

        OrderModel model = new OrderModel();

        //Kevin
        //need access to your array here
        ArrayList<Order> orderList = model.orderArrayList<>();

        //Kevin
        //need items from inside your array here
        orderList.add(new Builder().id(id)
                .orderCode(orderCode)
                .status(status)
                .requestingFacility(requestingFacility)
                .supplyingFacility(supplyingFacility)
                .program(program)
                .processingPeriod(processingPeriod)
                .createdDate(createdDate)
                .emergency(emergency)
                .build());

        Order[]orders = new Order[orderList.size()];
        orders = orderList.toArray(orders);
        return orders;

        //testorder
        //Order myOrder = new Order("new ip","new ordercode", "new status", "new requesting facility", "new supplier", "new program", "new proccessing period", "new date", false );

    }

}

