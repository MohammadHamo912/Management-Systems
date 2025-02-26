public class LongTermPatient extends PatientBase {
    private int daysAdmitted;
    private double dailyRate;


    public LongTermPatient() {
    }


    public LongTermPatient(String patientId, String name, int age, String medicalHistory, Department department, int daysAdmitted, double dailyRate) {
        super(patientId, name, age, medicalHistory, department);
        this.daysAdmitted = daysAdmitted;
        this.dailyRate = dailyRate;
    }


    public int getDaysAdmitted() {
        return daysAdmitted;
    }

    public void setDaysAdmitted(int daysAdmitted) {
        this.daysAdmitted = daysAdmitted;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
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
        for (int i = 0; i < super.getServices().size(); i++) {
            servicesCost += super.getServices().get(i).getServiceCost();
        }

        double bill = (this.daysAdmitted * this.dailyRate + servicesCost) * (1 + TAX_RATE);
        return bill;
    }


}
