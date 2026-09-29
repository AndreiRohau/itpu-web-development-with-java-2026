package com.arohau.rest;

// Hypermedia Controls
public class ControllerV3 {

    // DoctorController.java

    // GET my-app.com/doctor/{doctor_name/id}
    public Object getData(Object request) {
        Object response = new Object();
        return response;
    }

    // POST my-app.com/doctor - new doctor info in the body to be saved to doctors table in the database
    public Object postData(Object request) {
        Object response = new Object();
        return response;
    }

    // PUT my-app.com/doctor/{doctor_name/id}
    public Object updateData(Object request) {
        Object response = new Object();
        return response;
    }

    // PATCH my-app.com/doctor/{doctor_name/id}
    public Object updatePartOfData(Object request) {
        Object response = new Object();
        return response;
    }

    // DELETE my-app.com/doctor/{doctor_name/id}
    public Object deleteData(Object request) {
        Object response = new Object();
        return response;
    }


    // SlotController.java

    // GET my-app.com/slots ->> getting list of slots
    // GET my-app.com/slots/{slot_id}  ->> getting a specific slot by ID
    public Object getSlot(Object request) {
        Object response = new Object();
        return response;
    }

    // POST my-app.com/slots/{slot_id} - new slot data in the body
    public Object saveNewSlot(Object request) {
        Object response = new Object();
        return response;
    }

    // PUT my-app.com/slots/{slot_id} - updated data for the existing slot in the body
    public Object updateSlot(Object request) {
        Object response = new Object();
        return response;
    }

    // PATCH my-app.com/slots/{slot_id} - part of data for the existing slot in the body
    public Object updatePartOfSlotData(Object request) {
        Object response = new Object();
        return response;
    }

    // DELETE my-app.com/slots/{slot_id} - probably nothing in the body
    public Object deleteSlot(Object request) {
        Object response = new Object();
        return response;
    }
}
