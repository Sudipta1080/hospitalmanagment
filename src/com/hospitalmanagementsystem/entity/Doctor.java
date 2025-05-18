package com.hospitalmanagementsystem.entity;

public class Doctor {

    private int doctor_id;
    private String doctor_name;
    private String doctor_specialization;

    public Doctor(int doctor_id, String doctor_name, String doctor_specialization) {
        super();
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.doctor_specialization = doctor_specialization;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getDoctor_specialization() {
        return doctor_specialization;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setDoctor_specialization(String doctor_specialization) {
        this.doctor_specialization = doctor_specialization;
    }

    @Override
    public String toString() {
        return "[ " + this.doctor_id + " -> " + this.doctor_name + " -> " + this.doctor_specialization + " ]";
    }

}
