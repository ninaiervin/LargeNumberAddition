
//Nina Ervin
//BIT 142
//2/4/2023
//winter quarter
import java.util.*;
import java.io.*;

//this program is made to read a txt file of numbers and add them together as long as it is less then 25 digets.
public class Sum {
    //if you want to change the total digets just change the constant
    public static final int DIGETS = 25;
    public static void main(String[] args) throws FileNotFoundException {
    //this method is the main. here is where i creat the scanner to read the doc and create the two main arrays for adding. I waso call the intro and start all the adding in other methods
        Scanner input = new Scanner(new File("sum.txt"));
        int[] sum1 = new int [DIGETS];
        int[] sum2 = new int[DIGETS];
        intro();
        puttingItAllTogether(input, sum1, sum2);
    }



//this method introduces the progran and the goals of the program to the user
    public static void intro() {
        System.out.println("this program will read from a ext file called \"sum.txt\"");
        System.out.println("it will then add up all the numbers on each line and report the total.");
        System.out.println("the output can be found in the console");
    }



//this method keeps track of the count of lines as well as making sure there is a next line to read then passing that line to the sumALine method
    public static void puttingItAllTogether(Scanner input, int[] sum1, int[] sum2) throws FileNotFoundException{
        //counts how many new lines to report at the end of the program
        int count = 0;
        while(input.hasNextLine()){
            //this gets the next line from the file and passes it along to the sumALine method
            String allAdd = "";
            if (input.hasNextLine()) {
                allAdd = input.nextLine();
            }
            sumALine(allAdd, sum1, sum2);
            //after its done with the line it sends the arrays beck to being set to zero so that the last line doesnt affect the next line
            reset(sum1);
            reset(sum2);
            count++;
        }
        System.out.println("Total lines = " + count);
    }

//this program takes in a line of numbers and reads the first number. if there is more then 1 number then it sets the second into the sum2 and adds them together before moving on to the next number until all the numbers on the line are summed
    public static void sumALine(String allAdd, int[] sum1, int[] sum2) {
        //here is a new scanner made just to read just the line that youre woring with
        Scanner sA = new Scanner(allAdd);
        String num = sA.next();
        //this prints the number as a string before being converted to array so that you dont get all the zeros
        System.out.print(num + " ");
        convertToArrays(num, sum1);
        //this is where anyoverfolw nubers go so that it can be added to the next diget up.
        int remdor = 0;
        while(sA.hasNext()){
            //this takes in the next number to be added to the running total
            num = sA.next();
            System.out.print("+ " + num + " ");
            //you reset each time to make sure that the last number isnt effecting the next one
            reset(sum2);
            convertToArrays(num, sum2);
            //here is where you add each int one by one
            for(int i = (DIGETS-1); i>=0; i--){
                //this takes the sum of each number plus any remdor
                int Sum = sum1[i] + sum2[i]+remdor;
                //this only takes the last diget and assigns it to that slot in the array
                sum1[i] = Sum % 10;
                //if there is any second diget it gets added to the randor to then get added to the next number
                remdor = Sum/10;
            }
        } 
        //this prints out the final total for each line
        System.out.print("= ");
        //this tells me what index to start at so i dont get any leading zeros
        int index = beGoneZeros(sum1);
        for(int i = index; i<DIGETS; i++){
            System.out.print(sum1[i]);
        }
        System.out.println();
    }


//this method takes in the final array for each line and finds the start of each number. it then returns that number to sumLine to print the final number.
    public static int beGoneZeros(int[] sum1){
        for(int i = 0; i < DIGETS; i++){
            if(sum1[i] != 0){
                return i;
            }
        }
        return (DIGETS-1);
    }

//this method gets called by other methods to take a string and convrt it into an array
    public static void convertToArrays(String num, int[] workingArray){
        int count = 0;
        while(count < num.length()){
            workingArray[(DIGETS-1)-count] = Character.getNumericValue(num.charAt(num.length()-1-count));
            count++;
        }
    }


    //this metods job is to reset all the arrays beck to zero to the numbers dont get mixed up
    public static void reset(int[] sum){
        for(int i = 0; i < DIGETS; i++)
            sum[i] = 0;
    }
}

//for(int i = 0; i<DIGETS; i++){
//    System.out.print(sum1[i]);
//}