package com.hospitalmanagementsystem.hospitalservice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import com.hospitalmanagementsystem.entity.Appointment;

public class AppointmentService {

	private Connection con;

	public AppointmentService(Connection con) {
		this.con = con;
	}

	public int bookAppointment(int userId, int DocId, String appointmentdate) {
		// Book an appointment
		try {
			int insertedId = 0;
			String sql = "INSERT INTO APPOINTMENTS(PATIENT_ID, DOCTOR_ID, APPOINTMENT_DATE, IS_ACTIVE) VALUES(?,?,?,?)";
			LocalDate date = LocalDate.parse(appointmentdate);
			Date appDate = Date.valueOf(date);
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, userId);
			ps.setInt(2, DocId);
			ps.setDate(3, appDate);
			ps.setBoolean(4, true);
			int result = ps.executeUpdate();
			if (result > 0) {
				// Get the generated keys
				try (ResultSet rs1 = ps.getGeneratedKeys()) {
					if (rs1.next()) {
						int generatedId = rs1.getInt(1);
						insertedId = generatedId;
						System.out.println("Appointment ID: " + generatedId);
					}
				}
			} else {
				System.out.println("Insert failed, no rows affected.");
			}
			String docsql = "Update Doctor_Availability set Current_appointment = current_appointment+1 where doctor_id = ? and availability_date = ?";
			PreparedStatement ps1 = con.prepareStatement(docsql);
			ps1.setInt(1, DocId);
			ps1.setDate(2, appDate);
			int i = ps1.executeUpdate();
			return insertedId;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public boolean checkAppointmentExists(int appointmentId) {
		try {
			String sql = "SELECT COUNT(*) FROM APPOINTMENTS WHERE APPOINTMENT_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, appointmentId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int num = rs.getInt(1);
			if (num > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Appointment getAppointmentDetails(int appointmentId) {
		try {
			String sql = "SELECT * FROM APPOINTMENTS WHERE APPOINTMENT_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, appointmentId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int appId = rs.getInt(1);
			int patientId = rs.getInt(2);
			int docId = rs.getInt(3);
			Date appDate = rs.getDate(4);
			boolean isActive = rs.getBoolean(5);
			Appointment appointment = new Appointment(appointmentId, docId, patientId, appDate, isActive);
			return appointment;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String cancelAppointment(int appointmentId) {
		if (checkAppointmentExists(appointmentId)) {
			try {
				String sql = "UPDATE APPOINTMENTS SET IS_ACTIVE = FALSE WHERE APPOINTMENT_ID = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, appointmentId);
				int rs = ps.executeUpdate();
				if (rs > 0) {
					Appointment appointment = getAppointmentDetails(appointmentId);
					int docId = appointment.getDoctor_id();
					Date appDate = appointment.getAppointment_date();
					String docsql = "Update Doctor_Availability set Current_appointment = current_appointment-1 where doctor_id = ? and availability_date = ?";
					PreparedStatement ps1 = con.prepareStatement(docsql);
					ps1.setInt(1, docId);
					ps1.setDate(2, appDate);
					int i = ps1.executeUpdate();

					return "Your Appointment is Cancelled";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return "Please Enter Correct Appointment ID. No Data found with this appointment ID";
	}
	
	public void CheckallAppointment()
	{
		try {
			String sql = "SELECT A.APPOINTMENT_ID, P.PATIENT_NAME, D.DOCTOR_NAME, A.APPOINTMENT_DATE FROM APPOINTMENTS A INNER JOIN PATIENTS P ON A.PATIENT_ID = P.PATIENT_ID INNER JOIN DOCTORS D ON A.DOCTOR_ID = D.DOCTOR_ID WHERE IS_ACTIVE = TRUE";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			System.out.println("ID     |PATIENT NAME                  | DOCTOR NAME                    | APPOINTMENT DATE");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"              "+rs.getString(2)+"                   "+rs.getString(3)+"            "+rs.getDate(4));
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
