package com.hospitalmanagementsystem;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.hospitalmanagementsystem.entity.Patient;
import com.hospitalmanagementsystem.hospitalservice.PatientService;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
			Connection con = DataBaseConnect.getConnect();
			PatientService ps = new PatientService(con);
			//Creating Test
//			boolean result = ps.addPatient(new Patient(0000, "Kashif", 38, "Male"));
//			System.out.println(result);
			
			//Check the patient is exists or not
//			boolean exist = ps.checkPatientExist(10);
//			System.out.println(exist);
//			
			//Check patient by Id
//			Patient p = ps.getPatientById(3);
//			System.out.println(p);
			
			
			
			
			
		

	}

}
