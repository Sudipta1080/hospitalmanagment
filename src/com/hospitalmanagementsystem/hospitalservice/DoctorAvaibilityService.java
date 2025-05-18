package com.hospitalmanagementsystem.hospitalservice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hospitalmanagementsystem.entity.Doctor;
import com.hospitalmanagementsystem.entity.DoctorAvailability;

public class DoctorAvaibilityService {

	private Connection con;

	public DoctorAvaibilityService(Connection con) {
		this.con = con;
	}

	private DoctorService doctorService = new DoctorService(con);

	public List<Doctor> checkAvailibility(String spec, String appointmentDate) {
		try {
			String sql = "SELECT D.DOCTOR_ID, D.DOCTOR_NAME, D.DOCTOR_SPECIALIZATION FROM DOCTOR_AVAILABILITY DA INNER JOIN DOCTORS D ON D.DOCTOR_ID = DA.DOCTOR_ID WHERE DA.AVAILABILITY_DATE = ? AND DA.CURRENT_APPOINTMENT < DA.MAX_APPOINTMENT AND D.DOCTOR_SPECIALIZATION LIKE ?";
			// Converting the String to LocalDate
			LocalDate loc = LocalDate.parse(appointmentDate);
			// Converting LocalDate to Date
			Date sqlDate = Date.valueOf(loc);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, sqlDate);
			ps.setString(2, spec);
			// Fetching the docs ID from availability table
			ResultSet result = ps.executeQuery();
			List<Doctor> returnDocsList = new ArrayList<>();
			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				String spe = result.getString(3);
				Doctor doc = new Doctor(id, name, spe);
				returnDocsList.add(doc);

			}

			return returnDocsList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
