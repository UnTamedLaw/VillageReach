package com.lawk.villagereach;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DataClassTest {
    @Test
    public void testCredentials() {
        Credentials credentials = new Credentials("administrator", "password");
        assertThat(credentials.username, equalTo("administrator"));
        assertThat(credentials.password, equalTo("password"));
    }
    @Test
    public void testFacility() {
        Facility facility = new Facility();
    }
    @Test
    public void testFormActivityLineItemEditable() {
        FormActivityLineItemEditable formActivityLineItemEditable = new FormActivityLineItemEditable("id",0,0,0,null,null);
        formActivityLineItemEditable.setNotes("notes");
        formActivityLineItemEditable.setQuantityAccepted(99);
        formActivityLineItemEditable.setQuantityRejected(1);
        formActivityLineItemEditable.setRejectionReason("reason");
        assertThat(formActivityLineItemEditable.notes, equalTo("notes"));
        assertThat(formActivityLineItemEditable.quantityAccepted, equalTo(99));
        assertThat(formActivityLineItemEditable.quantityRejected, equalTo(1));
        assertThat(formActivityLineItemEditable.id, equalTo("id"));
    }
    @Test
    public void testLineItem() {
        LineItem lineItem = new LineItem();
    }

    @Test
    public void testOrder() {
        Order order = new Order();
    }
    @Test
    public void testOrderable() {
        Orderable orderable = new Orderable();
    }
    @Test
    public void testOrderableContent() {
        OrderableContent orderableContent = new OrderableContent();
    }
    @Test
    public void testProcessingPeriod() {
        ProcessingPeriod processingPeriod = new ProcessingPeriod();
    }
    @Test
    public void testProgram() {
        Program program = new Program();
    }
    @Test
    public void testProofOfDelivery() {
        ProofOfDelivery proofOfDelivery = new ProofOfDelivery();
    }
    @Test
    public void testProofOfDeliveryContent() {
        ProofsOfDeliveryContent proofsOfDeliveryContent = new ProofsOfDeliveryContent();
    }
    @Test
    public void testRequest() {
        Request request = new Request();
    }
    @Test
    public void testShipment() {
        Shipment shipment = new Shipment();
    }
    @Test
    public void testStub() {
        Stub stub = new Stub();
    }
}
