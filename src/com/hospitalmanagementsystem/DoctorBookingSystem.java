package com.hospitalmanagementsystem;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.hospitalmanagementsystem.entity.Doctor;
import com.hospitalmanagementsystem.entity.Patient;
import com.hospitalmanagementsystem.hospitalservice.AppointmentService;
import com.hospitalmanagementsystem.hospitalservice.DoctorAvaibilityService;
import com.hospitalmanagementsystem.hospitalservice.PatientService;

public class DoctorBookingSystem {

	public static void main(String[] args) {
		Connection con = DataBaseConnect.getConnect();
		Scanner sc = new Scanner(System.in);
		PatientService patientService = new PatientService(con);
		DoctorAvaibilityService doctorAvaibilityService = new DoctorAvaibilityService(con);
		AppointmentService appointmentService = new AppointmentService(con);
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("Do you want to continue ?(Y/N)");
			String choice = sc.nextLine();
			if (choice.equalsIgnoreCase("n")) {
				break;
			}

			System.out.println("1. Create New Patient \n" + "2. Book An Appointment \n"
					+ "3. Know Doctor Availibility \n" + "4. Cancel Appointment \n" +"5. See All The AppointMents : \n"+ " Enter Your Choice :");
			int uChoice = sc.nextInt();
			System.out.println("");
			switch (uChoice) {
			case 1:
				// Creating the user
				System.out.println("Enter Patient's Name : ");
				sc.nextLine();
				String name = sc.nextLine();
				System.out.println("Enter Patient's Age : ");
				int age = sc.nextInt();
				System.out.println("Enter Patient's Gender : ");
				sc.nextLine();
				String gender = sc.nextLine();
				System.out.println(name + " " + age + " " + gender);
				boolean result = patientService.addPatient(new Patient(0000, name, age, gender));
				if (result == true)
					System.out.println("Patient is created...");
				else
					System.out.println("Sorry please try again after some time...");
				break;
			case 2:
				// Booking the Appointment
				System.out.println("Enter The patient ID : ");
				int pid = sc.nextInt();
				boolean isPatientExists = patientService.checkPatientExist(pid);
				if (!isPatientExists) {
					System.out.println("Please create new patient...");
					sc.nextLine();
					continue;
				}

				System.out.println("Enter the Appointment Date (yyyy-mm-dd): ");
				sc.nextLine();
				String dateofBooking = sc.nextLine();
				System.out.println("Enter the Doctor Specilization : ");
				String spec = sc.nextLine();
				List<Doctor> doctors = doctorAvaibilityService.checkAvailibility(spec, dateofBooking);
				if (doctors.isEmpty()) {
					System.out.println("No Doctors are abvailable today...");
					continue;
				}
				System.out.println("Please select doctors ID from the List Below : ");
				System.out.println(
						"ID                      |  Name                          | Specilization               ");
				for (Doctor doctor : doctors) {
					System.out.println(doctor.getDoctor_id() + "                           " + doctor.getDoctor_name()
							+ "             " + doctor.getDoctor_specialization() + "  ");
				}

				int docsId = sc.nextInt();
				int id = appointmentService.bookAppointment(pid, docsId, dateofBooking);
				System.out.println("Appointment Booked Successfully... Thank You!!! Your Booking ID is : " + id);
				sc.nextLine();
				break;

			case 3:
				// Doctor Avaibility
				System.out.println("Please Enter the Date When you want to book your appointment (yyyy-mm-dd): ");
				String date = sc.nextLine();
				System.out.println("Enter which type of doctor you want to check : ");
				String specialization = sc.nextLine();
				List<Doctor> availableDoctors = doctorAvaibilityService.checkAvailibility(specialization, date);
				System.out.println("Available Doctors List Below : ");
				System.out.println(
						"ID                      |  Name                          | Specilization               ");
				for (Doctor doctor : availableDoctors) {
					System.out.println(doctor.getDoctor_id() + "                           " + doctor.getDoctor_name()
							+ "             " + doctor.getDoctor_specialization() + "  ");
				}

				break;

			case 4:
				// Cancel Appointment
				System.out.println("Enter Appointment ID : ");
				int appointmentId = sc.nextInt();
				String massege = appointmentService.cancelAppointment(appointmentId);
				System.out.println(massege);
				sc.nextLine();
				break;
				
			case 5:
				appointmentService.CheckallAppointment();
				sc.nextLine();
				break;
				
			default:
				System.out.println("Enter Correct Option... ");
				sc.nextLine();
				break;
			}
		}

	}

}
