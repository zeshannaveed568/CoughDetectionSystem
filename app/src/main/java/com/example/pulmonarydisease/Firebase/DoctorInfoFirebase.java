package com.example.pulmonarydisease.Firebase;

public class DoctorInfoFirebase {
    public String name, email, password, cnic, phone;



    public DoctorInfoFirebase() {




    }


    public DoctorInfoFirebase(String name, String email, String password, String cnic, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cnic = cnic;
        this.phone = phone;
    }

}
