package Code;

import java.util.*;
import java.io.*;

//HOW TO DECIDE WHICH DRAMA TO WATCH NEXT
//MINH TRUONG
/* 
What it does:
1.Takes in a file and read until there is no more lines left
2.generate a random num from the range 1 to the number of lines in a file
3.add num to list
4.if num is already in the list keep generating until a unique num is produced
5.print out the drama's name without the numbers
6.assign new numbers next to the dramas's name
 */
public class NextDrama {

    public static int numOfDramas = 0; //this is changed depending on how much dramas there are to watch

    public static void main(String args[]) throws IOException {
        File file = new File("C:\\Users\\Minh\\Desktop\\drama.txt");    //the file of drama's name
        Scanner sc = new Scanner(file); //scans the file
        NextDrama method = new NextDrama(); //object to call getNum

        //assign variables
        int num = 0;
        String drama = "";
        
        numOfDramas = method.getLines();    //call getLines method

        List<Integer> used = new ArrayList<>();  //a list of numbers that are already used
        List<String> dramaList = new ArrayList<>(); //a list of the the dramas
        
        
        while (sc.hasNextLine()) { // while the file has a nextLine

            drama = sc.nextLine(); // read the dramas name

            if(drama.length() > 0){
                dramaList.add(drama.substring(0, (drama.length() - 2))); // adds to the dramaList

                num = method.getNum(numOfDramas); // call getNum

                while (used.contains(num)) { // this is used to generate unique numbers
                    // while num is already in the list do this

                    num = method.getNum(numOfDramas);

                }

                used.add(num); // adds the number to the list
            }

        }
        sc.close();
        
        //sorting
        int[] numArray = new int[method.getLines()];
        String[] dramaArray = new String[method.getLines()];
        
        //initializes the new arrays with the values from the array list
        for(int c = 0; c < used.size(); c++){
            numArray[c] = used.get(c);
            dramaArray[c] = dramaList.get(c);
        }

        int itemTT,j;
        String itemST;
        boolean keepGoing;
        
        for(int k = 1; k < numArray.length;k++){    //start at the first next value because we are going backwards
            
            itemTT = numArray[k];   //assigns the index k value to itemTT (What num you want to insert)
            itemST = dramaArray[k]; //assigns the index k value to itemST (What drama you want to insert)
            
            j = k - 1;  //this is the previous index
            keepGoing = true;
            
           while(j >= 0 && keepGoing){
               
               if(itemTT < numArray[j]){    //if the value of itemTT is less than the previous index value
                   
                   numArray[j + 1] = numArray[j];   //exchange the value of the larger (previous) number to the index of the next (smaller) number
                   dramaArray[j + 1] = dramaArray[j];   //give the value of the previous to the next
                   j--; //decrement by 1
                   
                   if(j==-1){   //checks to make sure that it is at the end of the array
                       numArray[0] = itemTT;
                       dramaArray[0] = itemST;
                   }
                   
                   
               }
               else{
                   keepGoing = false;
                   numArray[j + 1] = itemTT;    
                   dramaArray[j + 1] = itemST;
               }
               
               
           }
            
        }
        
        //prints out the info
        for(int t = 0 ; t < dramaArray.length; t++){
            System.out.println(dramaArray[t] + " " + numArray[t]);
        }
        
        System.out.println("Your next drama to watch is: " + method.getNum(numOfDramas));   //print out what to watch next

    }

    //method of generating random numbers
    public int getNum(int n) {
        int num;
        int numOfDramas = n;
        Random rn = new Random();
        num = rn.nextInt(numOfDramas) + 1;
        return num;
    }
    
    //method to count how many lines in the files
    //reads the files and while sc.hasNextLine lines++
    public int getLines() throws IOException{
        int lines = 0;
        File file = new File("C:\\Users\\Minh\\Desktop\\drama.txt");
        Scanner sc = new Scanner(file);
        
        while(sc.hasNextLine()){
            sc.nextLine();
            lines++;
            
        }
        sc.close();
        return lines;
        
    }

}