import package as fn
from package import *
fn.medicalSystemSetUP()

def menu():
    print("Welcome to The Clinical System")
    all_tests = open("medicalTest.txt", "r")
    while True:
        print("1- Add new medical record")
        print("2- Add new medical test")
        print("3- update an existing patient medical record")
        print("4- update an existing medical test")
        print("5- display patient medical record")
        print("6- display patient medical test")
        print("7- delete an existing medical record")
        print("8- delete an existing medical test")
        print("9- generate textual summary report")
        print("10 - export medical records to a csv file")
        print("11 - import medical records from a csv file")
        print("0- exit")

        choice = int(input("Enter your choice: "))

        if choice == 1:
            fn.addNewMedicalRecord()
        elif choice == 2:
            fn.addNewMedicalTest()
        elif choice == 3:
            fn.updateMedicalRecord()
        elif choice == 4:
            fn.updateMedicalTest()
        elif choice == 5:
            fn.filterMedicalRecords()
        elif choice == 6:
            functions.filterMedicalTests()
        elif choice == 7:
            fn.deleteMedicalRecord()
        elif choice == 8:
            functions.deleteMedicalTest()
        elif choice == 9:
            functions.generateSummaryReports()
        elif choice == 10:
            fn.exportMedicalRecords()
        elif choice == 11:
            fn.importMedicalRecords()

        elif choice == 0:
            break
        else:
            print("Invalid choice")


menu()
fn.medicalSystemShutDown()
