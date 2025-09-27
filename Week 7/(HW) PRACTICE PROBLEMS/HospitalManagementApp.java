class MedicalStaff {
    protected String staffId;
    protected String name;
    protected String department;
    protected double salary;
    protected boolean isOnShift;
    
    public MedicalStaff(String staffId, String name, String department, double salary) {
        this.staffId = staffId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.isOnShift = false;
    }
    
    public void scheduleShift(String shiftTime) {
        isOnShift = true;
        System.out.println(name + " scheduled for " + shiftTime + " shift");
    }
    
    public void clockOut() {
        isOnShift = false;
        System.out.println(name + " clocked out");
    }
    
    public void accessHospital() {
        System.out.println("ID Card Access: " + name + " (" + staffId + ") entered hospital");
    }
    
    public void processPayroll() {
        System.out.println("Payroll: " + name + " - $" + salary + " processed");
    }
    
    public void showBasicInfo() {
        String shiftStatus = isOnShift ? "On Shift" : "Off Shift";
        System.out.println("Staff: " + name + " | ID: " + staffId + " | Dept: " + department + " | " + shiftStatus);
    }
    
    public String getName() {
        return name;
    }
}

class Doctor extends MedicalStaff {
    private String specialization;
    private int patientsToday;
    
    public Doctor(String staffId, String name, String department, double salary, String specialization) {
        super(staffId, name, department, salary);
        this.specialization = specialization;
        this.patientsToday = 0;
    }
    
    public void diagnosePatient(String patientName) {
        patientsToday++;
        System.out.println("Dr. " + name + " diagnosed " + patientName);
    }
    
    public void prescribeMedicine(String medicine) {
        System.out.println("Dr. " + name + " prescribed " + medicine);
    }
    
    public void performSurgery(String surgeryType) {
        System.out.println("Dr. " + name + " performed " + surgeryType + " surgery");
    }
    
    public void showBasicInfo() {
        super.showBasicInfo();
        System.out.println("Specialization: " + specialization + " | Patients today: " + patientsToday);
    }
}

class Nurse extends MedicalStaff {
    private String ward;
    private int medicinesAdministered;
    
    public Nurse(String staffId, String name, String department, double salary, String ward) {
        super(staffId, name, department, salary);
        this.ward = ward;
        this.medicinesAdministered = 0;
    }
    
    public void administerMedicine(String patientName, String medicine) {
        medicinesAdministered++;
        System.out.println("Nurse " + name + " gave " + medicine + " to " + patientName);
    }
    
    public void monitorPatient(String patientName) {
        System.out.println("Nurse " + name + " monitoring " + patientName);
    }
    
    public void assistProcedure(String procedure) {
        System.out.println("Nurse " + name + " assisting with " + procedure);
    }
    
    public void showBasicInfo() {
        super.showBasicInfo();
        System.out.println("Ward: " + ward + " | Medicines administered: " + medicinesAdministered);
    }
}

class Technician extends MedicalStaff {
    private String equipmentType;
    private int testsRun;
    
    public Technician(String staffId, String name, String department, double salary, String equipmentType) {
        super(staffId, name, department, salary);
        this.equipmentType = equipmentType;
        this.testsRun = 0;
    }
    
    public void operateEquipment(String equipment) {
        System.out.println("Technician " + name + " operating " + equipment);
    }
    
    public void runTest(String testType) {
        testsRun++;
        System.out.println("Technician " + name + " ran " + testType + " test");
    }
    
    public void maintainInstruments() {
        System.out.println("Technician " + name + " maintaining " + equipmentType + " equipment");
    }
    
    public void showBasicInfo() {
        super.showBasicInfo();
        System.out.println("Equipment: " + equipmentType + " | Tests run: " + testsRun);
    }
}

class Administrator extends MedicalStaff {
    private int appointmentsScheduled;
    private int recordsManaged;
    
    public Administrator(String staffId, String name, String department, double salary) {
        super(staffId, name, department, salary);
        this.appointmentsScheduled = 0;
        this.recordsManaged = 0;
    }
    
    public void scheduleAppointment(String patientName, String doctorName) {
        appointmentsScheduled++;
        System.out.println("Admin " + name + " scheduled appointment: " + patientName + " with Dr. " + doctorName);
    }
    
    public void manageRecords(String recordType) {
        recordsManaged++;
        System.out.println("Admin " + name + " updated " + recordType + " records");
    }
    
    public void showBasicInfo() {
        super.showBasicInfo();
        System.out.println("Appointments scheduled: " + appointmentsScheduled + " | Records managed: " + recordsManaged);
    }
}

public class HospitalManagementApp {
    
    public static void processStaffMember(MedicalStaff staff) {
        staff.accessHospital();
        staff.scheduleShift("Morning");
        staff.showBasicInfo();
        staff.processPayroll();
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("Hospital Management System");
        System.out.println();
        
        Doctor doctor = new Doctor("D001", "Smith", "Cardiology", 150000, "Heart Surgery");
        Nurse nurse = new Nurse("N001", "Johnson", "ICU", 65000, "ICU Ward");
        Technician tech = new Technician("T001", "Brown", "Radiology", 55000, "X-Ray");
        Administrator admin = new Administrator("A001", "Davis", "Administration", 45000);
        
        MedicalStaff[] hospitalStaff = new MedicalStaff[4];
        hospitalStaff[0] = doctor;
        hospitalStaff[1] = nurse;
        hospitalStaff[2] = tech;
        hospitalStaff[3] = admin;
        
        System.out.println("Hospital Staff Processing (Upcasting):");
        for (int i = 0; i < hospitalStaff.length; i++) {
            processStaffMember(hospitalStaff[i]);
        }
        
        System.out.println("Specific Staff Duties:");
        doctor.diagnosePatient("John Doe");
        doctor.prescribeMedicine("Heart Medication");
        
        nurse.administerMedicine("Jane Smith", "Pain Relief");
        nurse.monitorPatient("Bob Wilson");
        
        tech.runTest("Blood Test");
        tech.maintainInstruments();
        
        admin.scheduleAppointment("Mary Johnson", "Smith");
        admin.manageRecords("Patient");
        
        System.out.println();
        System.out.println("Upcasting Benefits:");
        System.out.println("- All staff types stored in same array");
        System.out.println("- Common operations work on any staff member");
        System.out.println("- Unified payroll and scheduling system");
        System.out.println("- Easy to add new staff types");
    }
}