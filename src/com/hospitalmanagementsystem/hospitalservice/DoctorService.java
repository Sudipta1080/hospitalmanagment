package com.hospitalmanagementsystem.hospitalservice;

import com.hospitalmanagementsystem.entity.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    private Connection con;

    public DoctorService(Connection con) {
        this.con = con;
    }

    //Add Doctor
    public boolean addDoctor(Doctor doctor) {
        try {
            String sql = "INSERT INTO DOCTORS(DOCTOR_NAME, DOCTOR_SPECIALIZATION) VALUES(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, doctor.getDoctor_name());
            ps.setString(2, doctor.getDoctor_specialization());
            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    //Delete Doctor
    public boolean removeDoctor(int docId) {
        try {
            String sql = "DELETE FROM DOCTORS WHERE DOCTOR_ID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, docId);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
    
    //Search all Docs
    
    public List<Doctor> getAllDoctors()
    {
    	try {
    		String sql = "SELECT * FROM DOCTORS";
    		Statement st = con.createStatement();
    		ResultSet rs = st.executeQuery(sql);
    		List<Doctor> doctors = new ArrayList<>();
    		while(rs.next())
    		{
    			int id = rs.getInt(1);
                String name = rs.getString(2);
                String spec = rs.getString(3);
                Doctor doctor = new Doctor(id, name, spec);
                doctors.add(doctor);
    		}
    		return doctors;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return null;
    }

    //Search Doctor by Specializantion
    public List<Doctor> getDocBySpecialization(String specialization) {

        try {
            String sql = "SELECT * FROM DOCTORS WHERE DOCTOR_SPECIALIZATION LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, specialization);
            ResultSet rs = ps.executeQuery();
            List<Doctor> doctors = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String spec = rs.getString(3);
                Doctor doctor = new Doctor(id, name, spec);
                doctors.add(doctor);
            }
            return doctors;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Serch Doctor by ID
    public Doctor searchByDocId(int id) {
        try {
            String sql = "SELECT * FROM DOCTORS WHERE DOCTOR_ID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int dId = rs.getInt(1);
            String name = rs.getString(2);
            String spec = rs.getString(3);
            Doctor doctor = new Doctor(dId, name, spec);
            return doctor;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
