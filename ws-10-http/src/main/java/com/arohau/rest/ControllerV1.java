package com.arohau.rest;

// multiple entrances to the resources - only one verb is used
public class ControllerV1 {

    // DoctorController.java

    // POST my-app.com/doctor/{doctor_name/id} - instruction are in the body
    public Object postRetrieveData(Object request) {
        Object response = new Object();
        return response;
    }

    // SlotController.java

    // POST my-app.com/slots/{slot_id} - instruction are in the body
    public Object postUpdateData(Object request) {
        Object response = new Object();
        return response;
    }
}
