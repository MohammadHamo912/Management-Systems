public class EmergencyPatient extends PatientBase{
    private double emergencyFee;

    public EmergencyPatient() {
    }


    public EmergencyPatient(String patientId, String name, int age, String medicalHistory, Department department,  double emergencyFee) {
        super(patientId, name, age, medicalHistory, department);
        this.emergencyFee = emergencyFee;
    }

    public double getEmergencyFee() {
        return emergencyFee;
    }

    public void setEmergencyFee(double emergencyFee) {
        this.emergencyFee = emergencyFee;
    }

    @Override
    public int compareTo(PatientBase other) {
        if (this.calculateBill() < other.calculateBill()) return -1;
        else if (this.calculateBill() > other.calculateBill()) return 1;

        return 0;
    }

    @Override
    public double calculateBill() {
        double servicesCost = 0;
        for(int i=0;i < super.getServices().size();i++){
            servicesCost += super.getServices().get(i).getServiceCost();
        }
        return this.emergencyFee + (servicesCost * (1 + TAX_RATE));
    }
}
