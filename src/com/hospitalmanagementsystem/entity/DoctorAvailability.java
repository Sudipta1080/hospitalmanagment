package com.hospitalmanagementsystem.entity;

import java.util.Date;

public class DoctorAvailability {
	private int id;
	private int doctor_id;
	private Date availability_date;
	private int max_appointment;
	private int current_appointment;
	public DoctorAvailability(int id, int doctor_id, Date availability_date, int max_appointment,
			int current_appointment) {
		super();
		this.id = id;
		this.doctor_id = doctor_id;
		this.availability_date = availability_date;
		this.max_appointment = max_appointment;
		this.current_appointment = current_appointment;
	}
	public int getId() {
		return id;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public Date getAvailability_date() {
		return availability_date;
	}
	public int getMax_appointment() {
		return max_appointment;
	}
	public int getCurrent_appointment() {
		return current_appointment;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public void setAvailability_date(Date availability_date) {
		this.availability_date = availability_date;
	}
	public void setMax_appointment(int max_appointment) {
		this.max_appointment = max_appointment;
	}
	public void setCurrent_appointment(int current_appointment) {
		this.current_appointment = current_appointment;
	}
	
	

}
