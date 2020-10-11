package view;
import model.Boat;
import model.MemberRegistry;

import java.io.File;
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
                printComposeEntry(entry);
            }
        }
        scan.close();

    }
    public void getComposeList() throws IOException {

        File file = new File("members.txt");    //File we will read from
        Scanner scan = new Scanner(file);

        System.out.println("Compose list of members:");
        while (scan.hasNextLine()) {                    //Read each line
            String line = scan.nextLine();
            String [] entry = line.split(" ");    //Split as each " "
            printComposeEntry(entry);                   //Call method for printing
        }
        scan.close();
    }
    public void getVerboseList() {
        System.out.println("Comming soon!");

    }
    public void printComposeEntry(String[] cEntry){
        System.out.print("Member ID: " +cEntry[0]);             //Take only the first part of entry and print
        System.out.print(", name: " +cEntry[1]);                //Take only the second part and print
        System.out.print(", person number: " +cEntry[2] +"\n"); //Take only the third part and print
    }
    public void printVerboseEntry(String vEntry){
        System.out.println("Comming soon!");
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
