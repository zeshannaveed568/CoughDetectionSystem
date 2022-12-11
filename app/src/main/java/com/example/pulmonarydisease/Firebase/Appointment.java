package com.example.pulmonarydisease.Firebase;

public class Appointment {

    String doctorName, etDate, etTime, patientName, patientAge, patientPhone, patientEmail;

    public Appointment() {
    }

    public Appointment(String patientName,String patientAge,String patientPhone, String patientEmail,String etDate, String etTime, String doctorName) {
        this.doctorName = doctorName;
        this.etDate = etDate;
        this.etTime = etTime;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientPhone = patientPhone;
        this.patientEmail = patientEmail;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getEtDate() {
        return etDate;
    }

    public void setEtDate(String etDate) {
        this.etDate = etDate;
    }

    public String getEtTime() {
        return etTime;
    }

    public void setEtTime(String etTime) {
        this.etTime = etTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }
}

