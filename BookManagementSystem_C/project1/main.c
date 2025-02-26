/*
    Name : Mohammad Hamo
    id Number : 1230394
    lecture section number : 7
    lab section number : 13

    phase 3
*/



#include <stdio.h>
#include <stdlib.h>
#define MAXSIZE 100
#define NAMESIZE 50


void displayMainMenu();   	                                // displays the main menu shown above
void addBook(char names[][NAMESIZE],int bins[],double prices[],int*size);   	    // adds information for a new book
void removeBook(char names[][NAMESIZE],int bins[],double prices[],int*size);  	    // removes information of old book
void searchForBook(char names[][NAMESIZE],int bins[],double prices[],int size); 	// searches for a book in the store
void printbooks(char names[][NAMESIZE],int bins[],double prices[],int size);       //(new function added in phase 2)


void uploadDataFile(char names[][NAMESIZE],int bins[],double prices[],int *size);     	// uploads book data from file
void updateDataFile(char names[][NAMESIZE],int bins[],double prices[],int size);    	// saves book data updates to file

int main()
{
   displayMainMenu();

    return 0;
}

void displayMainMenu(){
    printf("Welcome to My BookStore Management System: \n\n");
    int bins[MAXSIZE] ;
    double prices[MAXSIZE] ;
    int size =0;
    char names[50][NAMESIZE];


    uploadDataFile(names,bins,prices,&size);
    int userChoice;
    int reDisplayTheMenu = 1;

    while(reDisplayTheMenu){

    printf("Please Select an Operation (1-5):\n");
    printf("1- Add a Book:\n");
    printf("2- Remove A Book:\n");
    printf("3- Search for a Book:\n");
    printf("4- Print Book List:\n");
    printf("5- Exit System:\n \n");



    scanf("%d",&userChoice);


    switch(userChoice){
        case 1:
            addBook(names,bins,prices,&size);
            break;

        case 2:
            removeBook(names,bins,prices,&size);
            break;

        case 3:
            searchForBook(names,bins,prices,size);
            break;
        case 4:
        printbooks(names,bins,prices,size);
        break;


        case 5:
            updateDataFile(names,bins,prices,size);
            printf("Thank you for using My BookStore Management System. GoodBye.\n");
            reDisplayTheMenu = 0;
            break;


        default:
            printf("No such operation ! please try again.\n\n");
        }

    }
}
void addBook(char names[][NAMESIZE],int bins[],double prices[],int *size){
    int binNumber;
    double price;
    char name[NAMESIZE];
    if(*size >= MAXSIZE){
        printf("Error the library is full");
        return;

    }
    printf("Enter bin number for book\n");
    scanf("%d",&binNumber);
    int searchForPosition =0;// 10 15 16 20
    while(bins[searchForPosition] < binNumber && searchForPosition <*size){
        searchForPosition++;

    }
    int checkIfAdded = 0;

    if(bins[searchForPosition] == binNumber){
            printf("Error the bin number is already in bins\n\n");
            return;


    }else{


    printf("Enter name of book\n");
    scanf("%s",&name);
    printf("Enter price of book\n");
    scanf("%lf",&price);

    if(*size==0){
        strcpy(names[0],name);
        bins[0] = binNumber;
        prices[0] = price;
        *size +=1;
        checkIfAdded =1;
    }else{

        *size +=1;
         for(int i = *size -1;i> searchForPosition;i--){    // starting from the last index of the new size of array (which is now empty)
            strcpy(names[i],names[i-1]);
            bins[i] = bins[i-1];
            prices[i] = prices[i-1];

        }

        strcpy(names[searchForPosition],name);
        bins[searchForPosition]= binNumber;
        prices[searchForPosition] = price;
        checkIfAdded =1;

            }

    }

    if(checkIfAdded){
        printf("Book with bin %d has been added \n \n",binNumber);

    }


}
void removeBook(char names[][NAMESIZE],int bins[],double prices[],int *size){
    if(*size ==0){
        printf("Error : the library is empty\n\n");
        return;
    }
    int binNumber;
    printf("Enter bin number for book to remove\n");
    scanf("%d",&binNumber);

    for(int searchForPosition =0; searchForPosition < *size;searchForPosition++){
        if(bins[searchForPosition] == binNumber){
             for (int i = searchForPosition; i < *size - 1; i++) {
                strcpy(names[i],names[i+1]);
                bins[i] = bins[i + 1];
                prices[i] = prices[i + 1];
            }
            *size -=1;
            printf("Book with bin %d has been removed \n \n",binNumber);
            return;
        }
    }

    printf("Book with bin %d does not exist \n \n",binNumber);





}
void searchForBook(char names[][NAMESIZE],int bins[],double prices[],int size){
    if(size <= 0){
        printf("The library is Empty ,there is no books");
        return;
    }
    int binNumber;
    int checkIfBookIsFound =0;
    int bookIndex;
    printf("Enter bin number for book to search for\n");
    scanf("%d",&binNumber);

    for(int search =0;search < size;search++){
        if(bins[search] == binNumber){
            checkIfBookIsFound =1;
            bookIndex = search;
            break;
        }

    }

    if(checkIfBookIsFound ==1){
        printf(" Name = %s     bin# = %d     price = %.2lf \n \n",names[bookIndex],bins[bookIndex],prices[bookIndex]);

    }else{
        printf("There is no book with this Bin Number \n \n");

    }



}


void printbooks(char names[][NAMESIZE],int bins[],double prices[],int size){
   if(size <= 0){
        printf("The library is Empty ,there is no books\n\n");
        return;
    }

    for(int bookIndex =0;bookIndex < size ;bookIndex++ ){
         printf(" Name = %s     bin# = %d     price = %.2lf \n \n",names[bookIndex],bins[bookIndex],prices[bookIndex]);


    }


}





void uploadDataFile(char names[][NAMESIZE],int bins[],double prices[],int*size){
    FILE *file;
    file =fopen("books.txt","r");
    while(fscanf(file, "%s %d %lf",&names[*size], &bins[*size],&prices[*size]) != EOF){


        (*size)++;
    }

    printf("Uploading data file ...\n");
    printf("Data File uploaded\n \n");
    fclose(file);
}
void updateDataFile(char names[][NAMESIZE],int bins[],double prices[],int size){
    printf("Updating data file ...\n");

    FILE *file;
    file = fopen("books.txt","w");
   for(int bookIndex =0;bookIndex < size ;bookIndex++ ){
        fprintf(file,"%s %d %lf\n",names[bookIndex],bins[bookIndex],prices[bookIndex]);
   }

    fclose(file);
 printf("Data File updated\n\n");


}
