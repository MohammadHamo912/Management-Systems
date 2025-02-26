import java.util.ArrayList;

public class Department {
    private String departmentId;
    private String name;
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<PatientBase> patients = new ArrayList<>();
    public Department() {
    }

    public Department(String departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public ArrayList<PatientBase> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<PatientBase> patients) {
        this.patients = patients;
    }


    public void addDoctor(Doctor doctor){
        if(!this.doctors.contains(doctor)){
            doctor.setDepartment(this);
            this.doctors.add(doctor);
            return;
        }
        System.out.println("Doctor already exists in this department");
    }

    public void addPatient(PatientBase patient){
        if(!this.patients.contains(patient)){
            patient.setDepartment(this);
            this.patients.add(patient);
            return;
        }
        System.out.println("Patient already belongs to this department");
    }




    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", name='" + name + '\'' +
                ", doctors=" + doctors +
                ", patients=" + patients +
                '}';
    }
}
