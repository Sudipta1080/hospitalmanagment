package com.hospitalmanagementsystem.entity;

import java.sql.Date;

public class Appointment {
	private int appointment_id;
	private int doctor_id;
	private int patient_id;
	private Date appointment_date;
	private boolean isActive;
	public Appointment(int appointment_id, int doctor_id, int patient_id, Date appointment_date, boolean isActive) {
		super();
		this.appointment_id = appointment_id;
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.appointment_date = appointment_date;
		this.isActive = isActive;
	}
	public int getAppointment_id() {
		return appointment_id;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public Date getAppointment_date() {
		return appointment_date;
	}
	public boolean isActive() {
		return isActive;
	}
	
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public void setAppointment_date(Date appointment_date) {
		this.appointment_date = appointment_date;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	

}
