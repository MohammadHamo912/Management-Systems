import java.util.ArrayList;

public abstract class PatientBase implements Comparable<PatientBase>,Billable{
    private String patientId;
    private String name;
    private int age;
    private String medicalHistory;
    private Department department;
    private ArrayList<HospitalService> services ;


    public PatientBase() {
    }

    // the used constructor in the driver class
   public PatientBase(String patientId, String name, int age, String medicalHistory, Department department) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.medicalHistory = medicalHistory;
        department.addPatient(this);
        this.services = new ArrayList<>();
    }


    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ArrayList<HospitalService> getServices() {
        return services;
    }

    public void setServices(ArrayList<HospitalService> services) {
        for(HospitalService service : services){
            this.services.add(service);
            service.setPatientBase(this);
        }
    }
    public void addService(HospitalService service) {
        this.services.add(service);
        service.setPatientBase(this);
    }


    public abstract double calculateBill();

    // Compare patients based on their total bill
    @Override
    public abstract int compareTo(PatientBase other);
    @Override
    public String toString() {
        return "PatientBase{" +
                "patientId='" + patientId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", medicalHistory='" + medicalHistory + '\'' +
                ", department=" + department +
                ", services=" + services +
                '}';
    }
}
