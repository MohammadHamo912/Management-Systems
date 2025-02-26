class MedicalRecord:
    records = []

    def __init__(self, patient_id, test, date, result, unit, status):
        self.patient_id = patient_id
        self.test = test
        self.date = date
        self.result = result
        self.unit = unit
        self.status = status
        if status == "Completed":
            from datetime import datetime, timedelta

            first = self.date
            second = self.test.getTimeToBeCompleted()

            first_datetime = datetime.strptime(first, "%Y-%m-%d %H:%M")
            second_parts = second.split("-")
            days = int(second_parts[0])
            hours = int(second_parts[1])
            minutes = int(second_parts[2])

            second_datetime = timedelta(days=days,hours=hours, minutes=minutes)
            result = first_datetime+second_datetime
            result_date = result.strftime("%Y-%m-%d %H:%M")


            self.result_date = result_date
        else:
            self.result_date = None

    def updateRecord(self, test=None, result=None, status=None, result_date=None):
        if test:
            self.test = test
        if result:
            self.result = result
        if status:
            self.status = status
        if result_date:
            self.result_date = result_date

    def addToMedicalRecord(self):
        abbreviation = "Unknown"
        if self.test is not None:
            abbreviation = self.test.getAbbreviation()
        with open("medicalRecord.txt", 'a') as file:
            record = f"{self.patient_id}: {abbreviation}, {self.date}, {self.result}, {self.unit}, {self.status}"
            if self.result_date:
                record += f", {self.result_date}"
            record += "\n"
            file.write(record)

    def remove(self):
        with open("medicalRecord.txt", 'r') as file:
            records = file.readlines()
        updated_records = []
        record_to_remove = f"{self.patient_id}: {self.test.getAbbreviation()}"
        for record in records:
            if not record.startswith(record_to_remove):
                updated_records.append(record)

        with open("medicalRecord.txt", 'w') as file:
            file.writelines(updated_records)

        file.close()



    def getStatus(self):
        return self.status

    def setPatientID(self, patient_id):
        self.patient_id = patient_id

    def setTest(self, test):
        self.test = test

    def setDate(self, date):
        self.date = date

    def setResult(self, result):
        self.result = result

    def setUnit(self, unit):
        self.unit = unit

    def setStatus(self, status):
        self.status = status

    def getTest(self):
        return self.test


    # toString()

    def __str__(self):
        abbreviation = "Unknown"
        if self.test is not None:
            abbreviation = self.test.getAbbreviation()
        return f"Patient ID: {self.patient_id}, Test: {abbreviation}, Date: {self.date}, Result: {self.result}, Status: {self.status}, Result_Date : {self.result_date}"
