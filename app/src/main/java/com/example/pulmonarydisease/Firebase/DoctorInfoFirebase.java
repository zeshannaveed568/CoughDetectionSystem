package com.example.pulmonarydisease.Firebase;

public class DoctorInfoFirebase {
    public String name, email, cnic, phone, type;



    public DoctorInfoFirebase() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DoctorInfoFirebase(String name, String email, String cnic, String phone, String type) {
        this.name = name;
        this.email = email;
        this.cnic = cnic;
        this.phone = phone;
        this.type = type;
    }
}
