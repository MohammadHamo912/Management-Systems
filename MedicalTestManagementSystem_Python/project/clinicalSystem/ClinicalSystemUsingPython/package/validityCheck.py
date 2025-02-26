def validPatientID(patient_id):
    patient_id = str(patient_id)
    if len(patient_id) != 7:
        return False
    for num in patient_id:
        if not num.isdigit():
            return False
    return True


def validTestAbbreviation(medical_tests, abbreviation):
    for abb in medical_tests:
        if abb.getAbbreviation() == abbreviation:
            return True
    return False


def upNormalResult(medical_tests, test_result, test_abbreviation):
    if not validTestAbbreviation(medical_tests, test_abbreviation):
        print("Invalid test name")
        return
    for test in medical_tests:
        if test_abbreviation == test.getAbbreviation():
            test_range = test.getRange()
            min_val = float(test_range[0])
            max_val = (test_range[1])

            if(min_val == 0 and max_val == 0):
                return False

            if(min_val == 0):
                if(max_val >= test_result):
                    return False
                else:
                    return True

            if(max_val == 0):
               if(test_result >= min_val):
                   return False
               else:
                   return True

            if(min_val <= test_result <= max_val):
                return False

            return True


def validDate(date):
    from datetime import datetime

    try:
        # Attempt to parse the date with the expected format
        valid_date = datetime.strptime(date, "%Y-%m-%d %H:%M")
        return valid_date
    except ValueError:
        print("Invalid date format. Please enter in the format YYYY-MM-DD hh:mm.")

# this needs edit
def validResult(result):
    for num in result:
        if not num.isdigit():
            return False
    return True


def validStatus(status):
    if status == "Pending" or status == "Completed" or status == "Reviewed":
        return True
    return False


def getUnit(medicalTests, testAbbreviation):
    for test in medicalTests:
        if testAbbreviation == test.getAbbreviation():
            return test.getUnit()

    return
