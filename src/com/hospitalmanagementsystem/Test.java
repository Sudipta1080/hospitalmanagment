package com.hospitalmanagementsystem;

import com.hospitalmanagementsystem.entity.Doctor;
import com.hospitalmanagementsystem.hospitalservice.AppointmentService;
import com.hospitalmanagementsystem.hospitalservice.DoctorAvaibilityService;
import com.hospitalmanagementsystem.hospitalservice.DoctorService;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        Connection con = DataBaseConnect.getConnect();
        //PatientService ps = new PatientService(con);
        //DoctorService doc = new DoctorService(con);

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
//			check doc by ID
//        Doctor doctor = doc.searchByDocId(2);
//        System.out.println(doctor);
        
//        Creating Doctor
//        boolean result = doc.addDoctor(new Doctor(0000,"shaan", "GENERAL PHYSICIAN"));
//        System.out.println(result);

        // Search Doc by Specialization
//        List<Doctor> doctors = doc.getDocBySpecialization("GENERAL PHYSICIAN");
//        System.out.println(doctors);
        
        //Delete doc by ID
//        boolean result = doc.removeDoctor(3);
//        System.out.println(result);
        
        
        DoctorAvaibilityService ser = new DoctorAvaibilityService(con);
//        LocalDate loc = LocalDate.parse("2025-05-22");
//        Date sqlDate = Date.valueOf(loc);
       // System.out.println(ser.checkAvailibility("GENERAL PHYSICIAN", "2025-05-21"));
        
        AppointmentService ap = new AppointmentService(con);
        ap.bookAppointment(2, 2, "2025-05-21");
//       
    }

}
