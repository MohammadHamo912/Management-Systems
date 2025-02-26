class MedicalTest:
    numberOfMedicalTests = 0
    medicalTestNames = []
    medicalTestAbbreviations = []

    def __init__(self, test_name, abbreviation, test_range, unit, time_to_be_completed):
        self.test_name = test_name
        self.abbreviation = abbreviation
        self.test_range = test_range
        self.unit = unit
        self.time_to_be_completed = time_to_be_completed
        MedicalTest.numberOfMedicalTests += 1
        MedicalTest.medicalTestNames.append(test_name)
        MedicalTest.medicalTestAbbreviations.append(abbreviation)

    def addMedicalTest(self, numberOfMedicalTests):
        open_medical_test_file = open("medicalTest.txt", "a")
        open_medical_test_file.write(
            f"{numberOfMedicalTests}. Name: {self.test_name} ({self.abbreviation}); Range: ")


        if int(self.test_range[0]) != 0:
            open_medical_test_file.write(
                f"> {self.test_range[0]}, ")

        if int(self.test_range[1]) != 0:
            open_medical_test_file.write(
                f"< {self.test_range[1]}"
            )

        open_medical_test_file.write(
            f"; Unit: {self.unit}, {self.time_to_be_completed} \n")
        open_medical_test_file.close()



    def printMedicalTest(self):

        print(f"Name: {self.test_name} ({self.abbreviation}); Range:",end = " ")

        if int(self.test_range[0]) != 0:
            print(
                f"> {self.test_range[0]},",end = " ")

        if int(self.test_range[1]) != 0:
            print(f"< {self.test_range[1]}",end = "")

        print(f"; Unit: {self.unit}, {self.time_to_be_completed} \n")




    def updateMedicalTest(self, test_name, abbreviation, test_range, unit, time_to_be_completed):
        self.test_name = test_name
        self.abbreviation = abbreviation
        self.test_range = test_range
        self.unit = unit
        self.time_to_be_completed = time_to_be_completed


#   def deleteMedicalTest(self, testName):
#        readMedicalTest = open("medicalTest.txt", "r")
#        lines = readMedicalTest.readlines()
#        writeMedicalTest = open("medicalTest.txt", "w")
#        for line in lines:
#            if not line.startswith(testName):
#                writeMedicalTest.write(line)


    def getTestName(self):
        return self.test_name
    def getAbbreviation(self):
        return self.abbreviation

    def getUnit(self):
        return self.unit

    def getRange(self):
        return self.test_range

    def getTimeToBeCompleted(self):
        return self.time_to_be_completed

    def remove(self):
        with open("medicalTest.txt", 'r') as file:
            tests = file.readlines()
        updated_tests = []
        test_to_remove = f"{self.test_name}: {self.abbreviation}"
        for test in tests:
            if not test.startswith(test_to_remove):
                updated_tests.append(test)

        with open("medicalRecord.txt", 'w') as file:
            file.writelines(updated_tests)

        file.close()
