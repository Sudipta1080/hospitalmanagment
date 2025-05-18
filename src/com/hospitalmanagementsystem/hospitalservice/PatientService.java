package com.hospitalmanagementsystem.hospitalservice;

import com.hospitalmanagementsystem.entity.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientService {

    private Connection con;

    public PatientService(Connection con) {
        this.con = con;
    }

    public boolean addPatient(Patient patient) {
        try {
            String sql = "insert into patients(patient_name, patient_age, gender) values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, patient.getPatient_name());
            ps.setInt(2, patient.getPatient_age());
            ps.setString(3, patient.getPatient_gender());

            int rs = ps.executeUpdate();
            if (rs > 0) {
                // Get the generated keys
                try (ResultSet rs1 = ps.getGeneratedKeys()) {
                    if (rs1.next()) {
                        long generatedId = rs1.getLong(1);
                        System.out.println("Patient ID: " + generatedId);
                    }
                }
            } else {
                System.out.println("Insert failed, no rows affected.");
            }
            if (rs > 0) {
                return true; 
            }else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean checkPatientExist(int id) {
        try {
            String sql = "select count(*) AS rcount from patients where patient_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt("rcount");
            if (count > 0) {
                return true; 
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public Patient getPatientById(int id) {
        try {
            String sql = "select * from patients where patient_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int pid = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                String gender = rs.getNString(4);
                Patient patient = new Patient(pid, name, age, gender);
                return patient;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
