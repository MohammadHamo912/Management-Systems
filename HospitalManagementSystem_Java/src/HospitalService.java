public class HospitalService {
    private String serviceName;
    private double serviceCost;
    private PatientBase patientBase;


    public HospitalService() {
    }

    public HospitalService(String serviceName, double serviceCost, PatientBase patient) {
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
        patient.addService(this);

       }


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public PatientBase getPatientBase() {
        return patientBase;
    }

    public void setPatientBase(PatientBase patientBase) {
        this.patientBase = patientBase;
    }


    @Override
    public String toString() {
        return "HospitalService{" +
                "serviceName='" + serviceName + '\'' +
                ", serviceCost=" + serviceCost +
                ", patientBase=" + patientBase +
                '}';
    }
}

