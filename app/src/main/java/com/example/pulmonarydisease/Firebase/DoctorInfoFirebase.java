package com.example.pulmonarydisease.Firebase;

public class DoctorInfoFirebase {
    public String name, email, cnic, phone;


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

    public DoctorInfoFirebase(String name, String email, String password, String cnic, String phone) {
        this.name = name;
        this.email = email;
        this.cnic = cnic;
        this.phone = phone;
    }
}
