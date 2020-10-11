package view;
import model.Boat;
import model.MemberRegistry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RegistryView {
    MemberRegistry memberRegistry = new MemberRegistry();
    public void getMemberInfo(String input) throws IOException {

        File file = new File("members.txt"); //File we will read from


        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {                  //read line for line
            String line = scan.nextLine();
            String [] entry = line.split(" ");
            if (entry[0].equals(input)) {             //If we find the user print it out
                printMember(entry);
            }
        }
        scan.close();

    }
    public void getComposeList() throws IOException {

        File file = new File("members.txt");    //File we will read from
        Scanner scan = new Scanner(file);

        System.out.println("Compose list of members:");
        while (scan.hasNextLine()) {//Read each line
            String line = scan.nextLine();
            String [] entry = line.split(" ");    //Split as each " "
            printMember(entry);                   //Call method for printing
        }
        scan.close();
    }
    public void getVerboseList() throws FileNotFoundException {
        File file = new File("members.txt");    //File we will read from
        Scanner scan = new Scanner(file);
        String id, type, length;
        int count = 0;

        System.out.println("Compose list of members:");
        while (scan.hasNextLine()) {//Read each line
            String line = scan.nextLine();
            String [] entry = line.split(" ");    //Split as each " "
            printMember(entry);                   //Call method for printing
            System.out.println("Boats: ");

            for (int i=3; i < entry.length; i++ ){
                if (i%3 == 0){
                    id = entry[i];
                    type = entry[i+1];
                    length = entry[i+2];
                    printBoat(id,type,length);
                }
            }
        }
        scan.close();
    }
    public void printMember(String[] entry){
        System.out.print("Member ID: " +entry[0]);             //Take only the first part of entry and print
        System.out.print(", name: " +entry[1]);                //Take only the second part and print
        System.out.print(", person number: " +entry[2] +"\n"); //Take only the third part and print
    }
    public void printBoat(String id, String type, String length){
        System.out.println("    BoatID: " + id + ", boat type: " + type + ", boat length: " + length);
    }

    public void changeMemberRegistryView(String input_id) {
        String uppdatedName, uppdatedPersonalNumber;
        Scanner scan = new Scanner(System.in);

        System.out.print("Set new name: ");
        uppdatedName = scan.next();
        System.out.print("Set new personal number: ");
        uppdatedPersonalNumber = scan.next();

        //Gets new info from user

        memberRegistry.updateRegistry(input_id,uppdatedName,uppdatedPersonalNumber);
        scan.close();
    }
    public void changeBoatRegistryView(String input_id) {
        Boat boat = new Boat();
        String uppdatedType, uppdatedLength;
        Scanner scan = new Scanner(System.in);

        System.out.println("1 - Sailboat, 2 - Motorsailer, 3 - Kayak/Canoe, 4 - Other");
        System.out.print("Choose new type: ");
        uppdatedType = scan.next();
        try{
            boat.setBoatType(uppdatedType);
        }catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
            changeBoatRegistryView(input_id);
        }
        System.out.print("Set new length: ");
        uppdatedLength = scan.next();
        try{
            Integer.parseInt(uppdatedLength);
        }catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
            changeBoatRegistryView(input_id);
        }

        //Gets new info from user

        memberRegistry.updateRegistry(input_id,boat.getBoatType(),uppdatedLength);

    }

}
