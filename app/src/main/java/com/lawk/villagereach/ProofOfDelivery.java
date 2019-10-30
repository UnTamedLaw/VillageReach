package com.lawk.villagereach;


import com.google.gson.annotations.SerializedName;

public class ProofOfDelivery {
    @SerializedName("shipment")
    public Shipment shipment;
    @SerializedName("status")
    public String status;
//  @SerializedName("lineItems")
    @SerializedName("receivedBy")
    public String receivedBy;
    @SerializedName("deliveredBy")
    public String deliveredBy;
    @SerializedName("id")
    public String id;
}
//public class ProofOfDelivery {
//    private String proofofdeliveryid;
//    private String orderableid;
//    private String productid;
//    private String productname;
//    private String productunit;
//    private int quantityordered;
//    private int quantityshipped;
//    private String lotid;
//    private int quantityaccepted;
//    private int quantityrejected;
//    private String rejectionreasonid;
//    private String notes;
//    private String deliveredby;
//    private String recievedby;
//    private Date date;
//    private String status;
//
//    public ProofOfDelivery(
//            String proofofdeliveryid,
//            String orderableid,
//            String productid,
//            String productname,
//            String productunit,
//            int quantityordered,
//            int quantityshipped,
//            String lotid,
//            int quantityaccepted,
//            int quantityrejected,
//            String rejectionreasonid,
//            String notes,
//            String deliveredby,
//            String recievedby,
//            Date date,
//        String status){
//
//        this.proofofdeliveryid = proofofdeliveryid;
//        this.orderableid = orderableid;
//        this.productid = productid;
//        this.productname = productname;
//        this.productunit = productunit;
//        this.quantityordered = quantityordered;
//        this.quantityshipped = quantityshipped;
//        this.lotid = lotid;
//        this.quantityaccepted = quantityaccepted;
//        this.quantityrejected = quantityrejected;
//        this.rejectionreasonid = rejectionreasonid;
//        this.notes = notes;
//        this.deliveredby = deliveredby;
//        this.recievedby = recievedby;
//        this.date = date;
//        this.status = status;
//    }
//    public String getProofofdeliveryid(){
//        return proofofdeliveryid;
//    }
//
//    public void setProofofdeliveryid(String proofofdeliveryid){
//        this.proofofdeliveryid = proofofdeliveryid;
//    }
//
//    public String getOrderableid(){
//        return orderableid;
//    }
//
//    public void setOrderableid(String orderableid){
//        this.orderableid = orderableid;
//    }
//
//    public  String getProductid() {
//        return productid;
//    }
//
//    public void setProductid(String productid){
//
//        this.productid = productid;
//    }
//
//    public String getProductname(){
//        return productname;
//    }
//
//    public void setProductname(String productname){
//        this.productname = productname;
//    }
//
//    public  String getProductunit() {
//        return productunit;
//    }
//    public void setProductunit(String productunit){
//
//        this.productunit= productunit;
//    }
//
//    public  int getQuantityordered() {
//        return quantityordered;
//    }
//
//    public void setQuantityordered(int quantityordered){
//        this.quantityordered= quantityordered;
//    }
//
//    public  int getQuantityshipped() {
//        return quantityshipped;
//    }
//
//    public void setQuantityshipped(int quantityshipped){
//
//        this.quantityshipped= quantityshipped;
//    }
//
//    public  String getLotid() {
//        return lotid;
//    }
//
//    public void setLotid(String lotid){
//
//        this.lotid= lotid;
//    }
//
//    public  int getQuantityaccepted() {
//        return quantityaccepted;
//    }
//
//    public void setQuantityaccepted(int quantityaccepted){
//
//        this.quantityaccepted = quantityaccepted;
//    }
//
//    public  int getQuantityrejected() {
//        return quantityrejected;
//    }
//
//    public void setQuantityrejected(int quantityrejected){
//
//        this.quantityrejected = quantityrejected;
//    }
//
//    public  String getRejectionreasonid() {
//        return rejectionreasonid;
//    }
//
//    public void setRejectionreasonid(String rejectionreasonid){
//
//        this.rejectionreasonid = rejectionreasonid;
//    }
//
//    public  String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes){
//
//        this.notes = notes;
//    }
//
//    public  String getDeliveredby() {
//        return deliveredby;
//    }
//
//    public void setDeliveredby(String deliveredby){
//
//        this.deliveredby = deliveredby;
//    }
//
//    public  String getRecievedby() {
//        return recievedby;
//    }
//
//    public void setRecievedby(String recievedby){
//
//        this.recievedby = recievedby;
//    }
//
//    public  Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date){
//
//        this.date = date;
//    }
//
//    public  String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status){
//
//        this.status = status;
//    }
//
//}
