import java.util.*;

// Patient class to store patient information
class Patient {
    private String patientId;
    private String patientName;
    private int age;
    private String gender;
    private String contactInfo;
    private String[] medicalHistory;
    private String[] currentTreatments;
    
    // Constructor
    public Patient(String patientId, String patientName, int age, String gender, 
                   String contactInfo, String[] medicalHistory, String[] currentTreatments) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.medicalHistory = medicalHistory;
        this.currentTreatments = currentTreatments;
        HospitalManagement.totalPatients++;
    }
    
    // Getters and Setters
    public String getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getContactInfo() { return contactInfo; }
    public String[] getMedicalHistory() { return medicalHistory; }
    public String[] getCurrentTreatments() { return currentTreatments; }
    
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public void setMedicalHistory(String[] medicalHistory) { this.medicalHistory = medicalHistory; }
    public void setCurrentTreatments(String[] currentTreatments) { this.currentTreatments = currentTreatments; }
    
    // Add medical history entry
    public void addMedicalHistory(String entry) {
        String[] newHistory = Arrays.copyOf(medicalHistory, medicalHistory.length + 1);
        newHistory[medicalHistory.length] = entry;
        this.medicalHistory = newHistory;
    }
    
    // Add current treatment
    public void addCurrentTreatment(String treatment) {
        String[] newTreatments = Arrays.copyOf(currentTreatments, currentTreatments.length + 1);
        newTreatments[currentTreatments.length] = treatment;
        this.currentTreatments = newTreatments;
    }
    
    @Override
    public String toString() {
        return String.format("Patient ID: %s, Name: %s, Age: %d, Gender: %s, Contact: %s", 
                           patientId, patientName, age, gender, contactInfo);
    }
}

// Doctor class to store doctor information
class Doctor {
    private String doctorId;
    private String doctorName;
    private String specialization;
    private String[] availableSlots;
    private int patientsHandled;
    private double consultationFee;
    
    // Constructor
    public Doctor(String doctorId, String doctorName, String specialization, 
                  String[] availableSlots, double consultationFee) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.availableSlots = availableSlots;
        this.patientsHandled = 0;
        this.consultationFee = consultationFee;
    }
    
    // Getters and Setters
    public String getDoctorId() { return doctorId; }
    public String getDoctorName() { return doctorName; }
    public String getSpecialization() { return specialization; }
    public String[] getAvailableSlots() { return availableSlots; }
    public int getPatientsHandled() { return patientsHandled; }
    public double getConsultationFee() { return consultationFee; }
    
    public void setAvailableSlots(String[] availableSlots) { this.availableSlots = availableSlots; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }
    
    // Increment patients handled
    public void incrementPatientsHandled() {
        this.patientsHandled++;
    }
    
    // Check if doctor is available at specific slot
    public boolean isAvailable(String timeSlot) {
        for (String slot : availableSlots) {
            if (slot.equals(timeSlot)) {
                return true;
            }
        }
        return false;
    }
    
    // Remove time slot when booked
    public void bookSlot(String timeSlot) {
        List<String> slotList = new ArrayList<>(Arrays.asList(availableSlots));
        slotList.remove(timeSlot);
        this.availableSlots = slotList.toArray(new String[0]);
    }
    
    @Override
    public String toString() {
        return String.format("Doctor ID: %s, Name: %s, Specialization: %s, Fee: $%.2f, Patients Handled: %d", 
                           doctorId, doctorName, specialization, consultationFee, patientsHandled);
    }
}

// Appointment class to manage appointments
class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String appointmentDate;
    private String appointmentTime;
    private String status; // "Scheduled", "Completed", "Cancelled"
    
    // Constructor
    public Appointment(String appointmentId, Patient patient, Doctor doctor, 
                      String appointmentDate, String appointmentTime) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = "Scheduled";
        HospitalManagement.totalAppointments++;
    }
    
    // Getters and Setters
    public String getAppointmentId() { return appointmentId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getAppointmentDate() { return appointmentDate; }
    public String getAppointmentTime() { return appointmentTime; }
    public String getStatus() { return status; }
    
    public void setStatus(String status) { this.status = status; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }
    
    // Complete appointment and process billing
    public void completeAppointment() {
        this.status = "Completed";
        this.doctor.incrementPatientsHandled();
        HospitalManagement.totalRevenue += this.doctor.getConsultationFee();
    }
    
    // Cancel appointment
    public void cancelAppointment() {
        this.status = "Cancelled";
    }
    
    @Override
    public String toString() {
        return String.format("Appointment ID: %s, Patient: %s, Doctor: %s, Date: %s, Time: %s, Status: %s", 
                           appointmentId, patient.getPatientName(), doctor.getDoctorName(), 
                           appointmentDate, appointmentTime, status);
    }
}

// Main Hospital Management System class
public class HospitalManagement {
    // Static variables
    public static int totalPatients = 0;
    public static int totalAppointments = 0;
    public static String hospitalName = "City General Hospital";
    public static double totalRevenue = 0.0;
    
    // Lists to store data
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;
    private Scanner scanner;
    
    // Constructor
    public HospitalManagement() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    // Add a new patient
    public void addPatient() {
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        
        System.out.print("Enter Contact Info: ");
        String contact = scanner.nextLine();
        
        System.out.print("Enter Medical History (comma-separated): ");
        String historyInput = scanner.nextLine();
        String[] history = historyInput.split(",");
        
        System.out.print("Enter Current Treatments (comma-separated): ");
        String treatmentInput = scanner.nextLine();
        String[] treatments = treatmentInput.split(",");
        
        Patient patient = new Patient(id, name, age, gender, contact, history, treatments);
        patients.add(patient);
        System.out.println("Patient added successfully!");
    }
    
    // Add a new doctor
    public void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();
        
        System.out.print("Enter Available Slots (comma-separated, e.g., 09:00,10:00,14:00): ");
        String slotsInput = scanner.nextLine();
        String[] slots = slotsInput.split(",");
        
        System.out.print("Enter Consultation Fee: ");
        double fee = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        
        Doctor doctor = new Doctor(id, name, specialization, slots, fee);
        doctors.add(doctor);
        System.out.println("Doctor added successfully!");
    }
    
    // Schedule an appointment
    public void scheduleAppointment() {
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Please add patients and doctors before scheduling appointments.");
            return;
        }
        
        System.out.print("Enter Appointment ID: ");
        String appointmentId = scanner.nextLine();
        
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        Patient patient = findPatientById(patientId);
        
        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }
        
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();
        Doctor doctor = findDoctorById(doctorId);
        
        if (doctor == null) {
            System.out.println("Doctor not found!");
            return;
        }
        
        System.out.print("Enter Appointment Date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter Appointment Time: ");
        String time = scanner.nextLine();
        
        if (!doctor.isAvailable(time)) {
            System.out.println("Doctor is not available at this time!");
            return;
        }
        
        Appointment appointment = new Appointment(appointmentId, patient, doctor, date, time);
        appointments.add(appointment);
        doctor.bookSlot(time);
        
        System.out.println("Appointment scheduled successfully!");
        System.out.printf("Consultation fee: $%.2f%n", doctor.getConsultationFee());
    }
    
    // Complete an appointment
    public void completeAppointment() {
        System.out.print("Enter Appointment ID to complete: ");
        String appointmentId = scanner.nextLine();
        
        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment == null) {
            System.out.println("Appointment not found!");
            return;
        }
        
        if (!appointment.getStatus().equals("Scheduled")) {
            System.out.println("Appointment is not in scheduled status!");
            return;
        }
        
        appointment.completeAppointment();
        System.out.println("Appointment completed successfully!");
        System.out.printf("Revenue added: $%.2f%n", appointment.getDoctor().getConsultationFee());
    }
    
    // Display all patients
    public void displayPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        
        System.out.println("\n=== PATIENTS LIST ===");
        for (Patient patient : patients) {
            System.out.println(patient);
            System.out.println("Medical History: " + Arrays.toString(patient.getMedicalHistory()));
            System.out.println("Current Treatments: " + Arrays.toString(patient.getCurrentTreatments()));
            System.out.println("---");
        }
    }
    
    // Display all doctors
    public void displayDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }
        
        System.out.println("\n=== DOCTORS LIST ===");
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
            System.out.println("Available Slots: " + Arrays.toString(doctor.getAvailableSlots()));
            System.out.println("---");
        }
    }
    
    // Display all appointments
    public void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        
        System.out.println("\n=== APPOINTMENTS LIST ===");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
            System.out.printf("Consultation Fee: $%.2f%n", appointment.getDoctor().getConsultationFee());
            System.out.println("---");
        }
    }
    
    // Display hospital statistics
    public void displayStatistics() {
        System.out.println("\n=== HOSPITAL STATISTICS ===");
        System.out.println("Hospital Name: " + hospitalName);
        System.out.println("Total Patients: " + totalPatients);
        System.out.println("Total Appointments: " + totalAppointments);
        System.out.printf("Total Revenue: $%.2f%n", totalRevenue);
        System.out.println("Total Doctors: " + doctors.size());
        
        // Calculate completed appointments
        long completedAppointments = appointments.stream()
                .filter(apt -> apt.getStatus().equals("Completed"))
                .count();
        System.out.println("Completed Appointments: " + completedAppointments);
    }
    
    // Helper methods
    private Patient findPatientById(String patientId) {
        return patients.stream()
                .filter(p -> p.getPatientId().equals(patientId))
                .findFirst()
                .orElse(null);
    }
    
    private Doctor findDoctorById(String doctorId) {
        return doctors.stream()
                .filter(d -> d.getDoctorId().equals(doctorId))
                .findFirst()
                .orElse(null);
    }
    
    private Appointment findAppointmentById(String appointmentId) {
        return appointments.stream()
                .filter(a -> a.getAppointmentId().equals(appointmentId))
                .findFirst()
                .orElse(null);
    }
    
    // Main menu
    public void showMenu() {
        while (true) {
            System.out.println("\n=== " + hospitalName + " Management System ===");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Complete Appointment");
            System.out.println("5. Display All Patients");
            System.out.println("6. Display All Doctors");
            System.out.println("7. Display All Appointments");
            System.out.println("8. Display Hospital Statistics");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    addDoctor();
                    break;
                case 3:
                    scheduleAppointment();
                    break;
                case 4:
                    completeAppointment();
                    break;
                case 5:
                    displayPatients();
                    break;
                case 6:
                    displayDoctors();
                    break;
                case 7:
                    displayAppointments();
                    break;
                case 8:
                    displayStatistics();
                    break;
                case 9:
                    System.out.println("Thank you for using " + hospitalName + " Management System!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    // Main method with sample data
    public static void main(String[] args) {
        HospitalManagement hospital = new HospitalManagement();
        
        // Add some sample data for testing
        System.out.println("Initializing with sample data...");
        
        // Sample patients
        String[] history1 = {"Diabetes", "Hypertension"};
        String[] treatments1 = {"Insulin", "Blood pressure medication"};
        Patient patient1 = new Patient("P001", "John Doe", 45, "Male", "123-456-7890", history1, treatments1);
        hospital.patients.add(patient1);
        
        String[] history2 = {"Asthma"};
        String[] treatments2 = {"Inhaler"};
        Patient patient2 = new Patient("P002", "Jane Smith", 32, "Female", "987-654-3210", history2, treatments2);
        hospital.patients.add(patient2);
        
        // Sample doctors
        String[] slots1 = {"09:00", "10:00", "11:00", "14:00", "15:00"};
        Doctor doctor1 = new Doctor("D001", "Dr. Michael Johnson", "Cardiology", slots1, 150.00);
        hospital.doctors.add(doctor1);
        
        String[] slots2 = {"09:30", "10:30", "13:00", "14:30", "16:00"};
        Doctor doctor2 = new Doctor("D002", "Dr. Sarah Williams", "Pulmonology", slots2, 120.00);
        hospital.doctors.add(doctor2);
        
        System.out.println("Sample data loaded successfully!");
        
        // Start the menu system
        hospital.showMenu();
    }
}