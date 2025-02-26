# Clinical System project01 Linux Lab
# Mohammad Hamo 1230394
# Yanal Omar 1230156
# section 6

     printf "Welcome to our Clincal System\n"
     printf "\n"





menu() {

	#This function is the main function in our system
	# It displays the menu of the system and transfer the user to his
	# purpose of using the clinical system
	# here we can call the following functions
        #add
        #search
        #update
        #delete
        #avg
        # we alse can exit the system




        while true
        do
        printf "\n1) Add a test record\n"
        printf "2) Search for an existing test record\n"
        printf "3) update an existing test record\n"
        printf "4) delete an existing test record\n"
        printf "5) find the average results of an existing test\n"
        printf "6) exit the system\n\n"

	choice=$(choiceCheck 6) # here we call the choiceCheck function (you can take a look at it (its under the important functions)

        case $choice in
        1) add;;
	2) search;;
	3) update;;
	4) delete;;
	5) average;;
	6) printf "Thanks for using our clinical System \n"
	exit 0 ;;


        esac


	done


}








add() {

	#This is the add function it asks the user to enter the needed
	# details for the test record and check the validity of each one
	# by sending its value to a specific function for each one customized
	# to check the validity


#--------------------------------------------------------------
        printf "Enter patient ID:\n"
        read patientID
        while notValidID $patientID
	do
		read patientID
	done

	# if the enterd patiendID is not valid then dont enter we read
	# another one from the use
	# we do the same as this for the following functions
#----------------------------------------------------------------
	printf "Enter test name: \n"
        read testName
	while notValidTestName $testName
	do
		read testName
	done
	#the same as the above for validation
#-------------------------------------------------------
        printf "Enter the result of the test: \n"
        read result
	finalResult=$(append0 "$result")
	#here we made a function called append0 to make sure the entered
	#value is represented as a float ( you can take look at its below )


	#we cant guess if the enterd result of the test is valid or not
	# so we accept any result entered from the user
#---------------------------------------------------------
        printf "Enter the status of the test:\n"
        read status
        while notValidStatus $status
	do
		 read status
	done

	#this code uses the same logic as above for validation

#----------------------------------------------------------------------

	#here we made gave the user two choices for entering the date
	#either he can enter the date manually ( and this means that the
	# test record is in the past )
	# or he can choose to enter the current date as the date for the test record

	printf "If you want to enter the date of the test manually enter 1\n"
	printf "If you want to enter the current date enter 2\n\n"

	testDateChoice=$(choiceCheck 2) # here we used the choiceCheck again

	if [ "$testDateChoice" == "1" ]
	then
		while true; do
	        read -p "Enter the Date :(YYYY-MM): " testDate

        	if validDate "$testDate";then
			#here we check if the entered date is a valid one
			# a valid date is represented in (yyyy-mm)
			# and its months only between 01-12
			break
        	else
        		printf "Invalid Date. Please try again.\n"
        	fi
		done


	else
		#else means that the user enterd 2 because we guaranteed
		# from the choiceCheck function that the user only can enter
		# values from 1 to 2 (inclusive)
		testDate=$(date +%Y-%m)
	 # the "%Y-%m is used to display the date in year month format

	fi

#-------------------------------------------------------------------

#here we grab the unit of the test from the medical test file
# in that file the unit is always last one after the :
	unit=$(grep "$testName" medicalTest.txt | cut -d':' -f4)

#-----------------------------------------------------------------------
#here we check if the information the user enterd is already in the
#test record, if it is in the record we tell the user ALREAY EXISTS
#otherwise we will add it to the medical record
	check=$(grep "$patientID: $testName, $testDate, $finalResult,$unit, $status" medicalRecord.txt)
        if [ -n "$check" ]
        then
        printf "\nTEST ALREADY EXISTS\n"
	return
	fi


#---------------------------------------------------------------------
	printf "%s: %s, %s, %s,%s, %s\n" "$patientID" "$testName" "$testDate" "$finalResult" "$unit" "$status" >> medicalRecord.txt
	#here we add all the correct informations to the medicalRecord.txt

	printf "Added Successfully\n"

}


search() {

#this function search for a certain tests from the medicalRecord.txt

printf "1) Search by patient id\n"
printf "2) Search For up normal tests\n"
printf "3) Exit\n"


choice=$(choiceCheck 3)
#we used the chocieCheck function to let the user only enter
#a value from 1 to 3

#-----------------------------------------------
if [ $choice == "3" ]
then
        printf "Back to MENU...\n\n\n"
        return
fi
#---------------------------------------
if [ $choice == "1" ]
then
searchByPatientId #go to searchByPatientId function
elif [ $choice == "2" ]
then
searchForUpNormal #go to seachforUpNormal function
fi

printf "Done searching\n\n\n"


}


searchByPatientId() {

	printf "Enter patient ID:\n"
        read patientID

	while notValidID $patientID
        do
	        read patientID
        done
	# this part of code is explained above in the add function
#-----------------------------------------------------------------
	if [ -z "$(grep "$patientID" medicalRecord.txt)" ]
	then
	printf "\nNo such patient\n"
	return
	fi

	#here we make sure that the entered patiedID exisits
	# in the medicalRecord.txt
#---------------------------------------------------------------


printf "Enter the number of the operation, What are you searching for :\n\n"
printf "1)Retrive all patient tests\n"
printf "2)Retrive all up normal patient tests\n"
printf "3)Retrive all patient tests in a given specific period\n"
printf "4)Retrive all patient test based on test status\n\n\n"

#here we gave the user the choice for what specific information
# he wents to get from the medicalRecord.txt

choice=$(choiceCheck 4)
#again we used the choiceCheck function (Explained above)

printf "\n" #used for good displayment for the user

#---------------------------------------------------

case $choice in
"1") #retrive all patient tests
grep "$patientID" medicalRecord.txt
;;
#--------------------
"2") #retrive the upnormal patient tests for a specific patient ID

searchForUpNormal "Hgb" >> temp.txt
searchForUpNormal "BGT" >> temp.txt
searchForUpNormal "LDL" >> temp.txt
searchForUpNormal "systole" >> temp.txt
searchForUpNormal "diastole" >> temp.txt

grep $patientID temp.txt

echo "" > temp.txt

;;
#---------------------------------------------------
"3") # retrive on a specific date
while true; do
	read -p "Enter the first Date :(YYYY-MM): " firstDate

        if validDate "$firstDate";then
	break
	else
	printf "Invalid Date. Please try again.\n"
	fi
done

#this part of code is explained above in the add function

while true; do
        read -p "Enter the End Date :(YYYY-MM): " secondDate

        if validDate "$secondDate";then
	break
        else
        echo "Invalid Date. Please try again."
        fi
done

#-----------------------------------------
# here after we got the first and last date we want to itereate
# between them and check a month by month in a year over a year
# and see if there is test record in this date and then grep it to the user
currentDate="$firstDate"

while [ $(echo "$currentDate" | tr -d '-') -le $(echo "$secondDate" | tr -d '-') ]
do
if [ "$currentDate" == "$secondDate" ]
then
break
fi
grep "$patientID" medicalRecord.txt | grep "$currentDate"

currentDate=$(next_month "$currentDate")

done

;;
#---------------------------------------------
"4")
printf " What status do you want to show :\n"
printf "1)Completed\n"
printf "2)Pending\n"
printf "3)Reviewed\n"

#this code is easy to read no need for comments

choice=$(choiceCheck 3)


if [ $choice -eq 1 ]
then
grep "$patientID" medicalRecord.txt | grep "Completed"

elif [ $choice -eq 2 ]
then
grep "$patientID" medicalRecord.txt | grep "Pending"

elif [ $choice -eq 3 ]
then
grep "$patientID" medicalRecord.txt | grep "Reviewed"

fi
;;
esac

printf "\n"

}



searchForUpNormal() {
#we use this function in two ways :
#the first one when enters the test name as an argument when calling function
#the second one when we want to allow the user to enter the test name
#that we want to grep the upnormal tests for.

if [ $# -eq 0 ]
# if the user didnt enter an argument that means we want
# to let the user to enter the test name
then
printf "\nEnter test Name\n"
read testName
else
testName=$1
fi

# here we grep the values for a certain test then check which one of these
# values is upnormal

values=$(grep "$testName" medicalRecord.txt | cut -d',' -f3)
for value in $values
do

valueUpnormalCheck=$(upnormalCheck "$testName" "value")
if [ $valueUpnormalCheck -eq 0 ]
then
grep "$testName" medicalRecord.txt | grep "$value"
fi
done


}





upnormalCheck() {
testName=$1
result=$2

# here we compare if the result is in the normal range we return 1
# if its not in the normal range (upnormal) it returns 0

case $testName in
"Hgb")
if [ $(echo "$result < 13.8" | bc) -eq 1 ] || [ $(echo "$result > 17.2" | bc) -eq 1 ]
then
echo 0
return
fi
;;

"BGT") if [ $(echo "$result < 70.0" | bc) -eq 1 ] || [ $(echo "$result > 99.0" | bc) -eq 1 ]
then
echo 0
return
fi
;;


"LDL") if [ $(echo "$result > 99.0" | bc) -eq 1 ]
then
echo 0
return
fi
;;


"systole") if [ $(echo "$result > 120.0" | bc) -eq 1 ]
then
echo 0
return
fi
;;


"diastole") if [ $(echo "$result > 80.0" | bc) -eq 1 ]
then
echo 0
return
fi
;;

esac



echo 1
return

}




delete() {
	printf "1) Delete all medical test records\n"
	printf "2) Delete patient medical test records\n"
	printf "3) Delete a medical test Record\n"
	printf "4) Exit\n"


	choice=$(choiceCheck 4)


	#here we delete the records on specific criteria

	case $choice in

	"1") #delete all records
	 echo "" > medicalRecord.txt
	;;
	#-----------------------------------------------
	"2") #delete all records for a patient
	printf "Enter patient ID:\n"
        read patientID
        while notValidID $patientID
        do
                read patientID
        done

	grep -v "$patientID" medicalRecord.txt > temp.txt
	mv temp.txt medicalRecord.txt
	;;
	#------------------------------------------------
	"3") #delete all records for a specific test
	 printf "Enter test name: \n"
        read testName
        while notValidTestName $testName
        do
                read testName
        done
	grep -v "$testName" medicalRecord.txt > temp.txt
	mv temp.txt medicalRecord.txt

	 ;;
	#-------------------------------------------------
	"4") #back to menu
	printf "Back to Menu..."
	 return
	;;
	esac






}










update() {

	printf "Enter patient ID:\n"
        read patientID
        while notValidID $patientID
        do
                read patientID
        done



	checkID=$(grep "$patientID" medicalRecord.txt)
	#make sure that the patientID exists
	if [ -z "$checkID" ]
	then
		printf "\nERROR: NO SUCH PATIENT\n"
		return
	else
		grep $patientID medicalRecord.txt
		printf "\nEnter the name of the test you want to change:\n"
	        read testName



		#make sure that the test exists for this patientID
		checkTest=$(grep "$patientID" medicalRecord.txt | grep "$testName")
		if [ -z "$checkTest" ]
		then
			printf "\nERROR: TEST NOT FOUND \n"
			return
		else
			while true; do
		                read -p "Enter the Date of the test you want to update:(YYYY-MM): " testDate

		                if validDate "$testDate";then
                	        #here we check if the entered date is a valid one
                        	# a valid date is represented in (yyyy-mm)
                       		# and its months only between 01-12
                        	break
                		else

				printf "Invalid Date. Please try again.\n"
        			fi
                	done

			checkTest2=$(grep "$patientID" medicalRecord.txt | grep "$testName" | grep "$testDate")
       			if [ -z "$checkTest2" ]
                	then
                        	printf "\nERROR: TEST NOT FOUND \n"
                        return
			fi
			printf "\nType the new result: \n"
			read newResult
			finalResult=$(append0 "$newResult")

			printf "\nType new Test status: \n"
			read status
			while notValidStatus $status
        		do
                		 read status
		        done

			grep -v "^$patientID: $testName, $testDate" medicalRecord.txt > temp.txt
			mv temp.txt medicalRecord.txt


		unit=$(grep "$testName" medicalTest.txt | cut -d':' -f4)
		printf "%s: %s, %s, %s,%s, %s\n" "$patientID" "$testName" "$testDate" "$finalResult" "$unit" "$status" >> medicalRecord.txt

		#all of the code here is explained in the add function


		fi
	fi
}






avg() {
#this code greps the values for a certian test and do the normal avg calculation
#it returs null if there is no values in the medical record for that test name

testName=$1
values=$(grep "$testName" medicalRecord.txt | cut -d',' -f3)
sum=0
count=0
for value in $values
do
	sum=$(echo "$sum + $value" | bc)
	count=$((count + 1))
done

if [ $count -gt 0 ]
then
        avgCalculate=$(echo "scale=2; $sum / $count" | bc)
	echo $avgCalculate
	return
elif [ $count -eq 0 ]
then
	return
fi

return


}


printAvg() {
#easy to understand code to print the testname and its avg

name=$1
val=$2

if [ -z "$val" ]
then
        printf "Not enough tests to calculate the average of %s\n" "$name"
else
	printf "Average %s: %.2f\n" "$name" "$val"
fi
return

}

average() {


	avgHgb=$(avg "Hgb") # here we get the the avg value
	printAvg "Hgb" $avgHgb #here we print it

	avgBGT=$(avg "BGT")
	printAvg "BGT" $avgBGT

	avgLDL=$(avg "LDL")
	printAvg "LDL" $avgLDL

	avgSystole=$(avg "systole")
	printAvg "systole" $avgSystole

	avgDiastole=$(avg "diastole")
	printAvg "diastole" $avgDiastole


	printf "Average Calculation is done\n"
	printf "Back to Menu....\n\n\n"



}










append0() {

	#this function checks if the variable has the float representation (0.0)
	#if it doesnt have that representation it append (.0) to it
	# we used the function when we compared between results and so on
	result="$1"
	if echo "$result" | grep -q "\."
	then
		echo "$result"
	else
		echo "${result}.0"
	fi
}



notValidID() {


	#this code checks if the entered value has the right patiedID form
	#if not it prints invalid and returns 0
	#if its valid ID returns 1

	# ( A valid patiend ID only consists of numeric digits and the length should be 7)
        patientID=$1
        nonIntegerCheck=$(echo "$patientID" | grep '[^0-9]')
        if [ -n "$nonIntegerCheck" ] || [ ${#patientID} -ne 7 ]

        then
                printf "Invalid Patient ID (please enter 7 numeric digits only)\n"
		return 0
        fi

	return 1
}






notValidTestName() {
	#this code checks if the test name is in nthe medicalTest.txt
	#so if we want to give access to the user to add a new test
	# we should add it to the medicalTest.txt first

        testName=$1
	result=$(grep "$testName" medicalTest.txt)

	if [ -z "$result" ]
	then
	printf "Invalid Test Name (please enter an existing test in this clinical)\n"
	return 0
	fi
	return 1

}


notValidStatus() {

	#there are only three status that the user can enter
	#other wise it will print invalid

        testStatus=$1
        if [ $testStatus != "Pending" ] && [ $testStatus != "Completed" ] && [ $testStatus != "Reviewed" ]
        then
	        printf "Invalid Status please enter a correct status\n"
		return 0
        fi
		return 1
}


validDate() {
	#this function checks if it has the valid Date representation (YYYY-MM)

	echo "$1" | grep -Eq "^[0-9]{4}-(0[1-9]|1[0-2])$"
 }


next_month() {

	#using this function will allow us to add a month to the given date
	#if the month is 12 it will automatically change the year and reset the month to 01

	date -d "$1-01 +1 month" +"%Y-%m"
}




choiceCheck() {


	#this function obligates the user to enter a number between 1 and another only inclusively
	#this helps us when deeling with the menus and when we want to confine the users choice
	#and not allowing them to enter a wrong choice

	temp=$1
	while true
	do
        read -p "Enter Number Between 1 and $temp only : " choice
        if [ ${#choice} -eq 1 ]
        then
        if [ $choice -ge 1 ] && [ $choice -le $temp ]; then
	        echo $choice
		break
fi
        fi

        done



}

# here we called the menu and started our program
menu
