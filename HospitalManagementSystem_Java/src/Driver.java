import java.util.ArrayList;
import java.util.Collections;

public class Driver {

    // Static method to sort doctors by salary
    public static void sortDoctorsBySalary(ArrayList<Doctor> doctors) {
       Collections.sort(doctors); // Uses the compareTo method in Doctor
    }

    // Static method to sort Patients by Bill
    public static void sortPatientsByBillAmount(ArrayList<PatientBase> patients) {
        Collections.sort(patients); // Uses the compareTo method in patients
    }

    // Static method to calculate total patients' bill
    public static double calculateTotalPatientsBill(ArrayList<PatientBase> patients) {
        double total = 0;
        for (PatientBase patient : patients) {
            total += patient.calculateBill();
            // iterate through the patients and add the bill for the patients to the total Sum
        }
        return total;
    }
    public static void generateBill(PatientBase patient) {
        System.out.println("Bill : ");
        System.out.printf("Patient ID: %s.\nName: %s.\nTotal Bill: %.2f.\n", patient.getPatientId(), patient.getName(), patient.calculateBill());
    }

    public static void main(String[] args) {
        // Departments "Create three Department objects".
        Department[] departments = new Department[3];
        Department d1 = new Department("depNo.1", "Emergency");
        Department d2= new Department("depNo.2", "Cardiology");
        Department d3= new Department("depNo.3", "Neurology");
        departments[0] = d1;
        departments[1] = d2;
        departments[2] = d3;

        //Patients
        /*
        Create an ArrayList of patients with three predefined patients (two
            EmergencyPatient, and one LongTermPatient).
         */
        ArrayList<PatientBase> patients = new ArrayList<>();
        patients.add(new EmergencyPatient("patientNo.123", "Mohammed Ahmad",20, "Heart disease", departments[0], 2423.55));
        patients.add(new EmergencyPatient("patientNo.456", "Khaled Mustafa", 29, "Stroke", departments[1], 4500.99));
        patients.add(new LongTermPatient("patientNo.333", "Saif Ameen", 50, "Hip Surgery", departments[2], 15, 1000));

        //Doctors
        // Create an ArrayList of doctors with two predefined doctors.
        ArrayList<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("drNo.55555", "Saleem Saleh", "Neurons surgeon", "PhD", 25000.0, 40.5, 250.0, departments[2]));
        doctors.add(new Doctor("drNo.66666", "Laith Amro", "Emergent Doctor", "Master", 22000.0, 22.0, 150.0, departments[0]));

        ArrayList<HospitalService> services = new ArrayList<>();
        //X-ray, MRI, lab tests
        // Create an ArrayList of hospital services and assign some services to patients.
        HospitalService service1 = new HospitalService("X-ray",1500,patients.get(1));
        HospitalService service2 = new HospitalService("MRI",1500,patients.get(2));
        HospitalService service3 = new HospitalService("lab tests",350,patients.get(0));
        services.add(service1);
        services.add(service2);
        services.add(service3);


        // Generate and display the third patient's bill
        generateBill(patients.get(2));

        System.out.println("\n");

        // Sort doctors by salary
        sortDoctorsBySalary(doctors);
        for(Doctor doctor : doctors){
            System.out.println("Doctor Department Name : " + doctor.getDepartment().getName() + ", Salary : " + doctor.calculateSalary());
        }
        System.out.println("\n");
        // Sort patients by Bill amount
        sortPatientsByBillAmount(patients);
        for(PatientBase patient : patients){
            System.out.println("Patient Name : " + patient.getName() + ", Age :" + patient.getAge());
        }

        System.out.println("\n");
        // Print total sum of all patients' bills
        double totalBill = calculateTotalPatientsBill(patients);
        System.out.println("Total Sum of All Patients' Bills: " + totalBill);
    }

}
