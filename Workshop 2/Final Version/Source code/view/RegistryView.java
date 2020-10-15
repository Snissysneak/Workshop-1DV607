package view;
import model.Boat;
import model.Member;
import model.MemberRegistry;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistryView {
    private MemberRegistry memberRegistry = new MemberRegistry();
    private Member rv_member = new Member();

    /*Check if record with given member exist and send data to printMember()*/
    public void getMemberInfo(String memberID) throws IOException {
        ArrayList<String[]> db = memberRegistry.readDatabase();
        for (String[] record : db)
            if (record[0].equals(memberID))
                printMember(record);
    }
    /*Check if given boat exist and send data to printBoat()*/
    public void getBoatInfo(String boatID) throws IOException {
        ArrayList<String[]> db = memberRegistry.readDatabase();
        for (String[] record : db) {
            for (int i = 0; i < record.length; i++) {
                if (record[i].equals(boatID)) {
                    printBoat(record[i],record[i+1],record[i+2]);
                }
            }
        }
    }
    /*Method prints compose list of members */
    public void getComposeList() throws IOException {
        ArrayList<String[]> db = memberRegistry.readDatabase();
        for (String[] record : db)
            printComposeEntry(record);

    }
    /* Print one record of compose list in given record */
    public void printComposeEntry(String[] entry){
        int boats = memberRegistry.getNumberOfBoats(entry);
        String boatsStr = Integer.toString(boats);
        System.out.print("Member ID: " + entry[0]);             //Take only the first part of entry and print
        System.out.print(", name: " + entry[1]);                //Take only the second part and print
        System.out.print(", owned boats: " + boatsStr + "\n"); //Take only the third part and print
    }
    /* Print verbose list with club members */
    public void getVerboseList() throws IOException {
        String id, type, length;
        ArrayList<String[]> db = memberRegistry.readDatabase();
        for (String[] record : db) {
            printMember(record);                    //Call method for printing
            System.out.println("Owned boats: ");
            for (int i = 3; i < record.length; i++) {
                if (i % 3 == 0) {
                    id = record[i];
                    type = record[i + 1];
                    length = record[i + 2];
                    printBoat(id, type, length);
                }
            }
            System.out.println("--------------------------------------------------------");
        }
    }
    /* Print information about member in given record */
    public void printMember(String[] entry){
        System.out.print("Member ID: " + entry[0]);             //Take only the first part of entry and print
        System.out.print(", name: " + entry[1]);                //Take only the second part and print
        System.out.print(", person number: " + entry[2] + "\n"); //Take only the third part and print
    }
    /*Print information about given Boat */
    public void printBoat(String id, String type, String length) {
        System.out.println("- boatID: " + id + ", boat type: " + type + ", boat length: " + length);
    }
    /*Display information about change member process*/
    public void changeMemberRegistryView(String memberID) throws IOException {
        String newName, newPersonNumber;
        Scanner sc  = new Scanner(System.in);

        System.out.print("Set new name: ");
        newName = sc.next();
        System.out.print("Set new personal number: ");
        newPersonNumber = sc.next();
        while (newPersonNumber.length() != 10 || !rv_member.checkLetter(newPersonNumber)) { //Check if the input is 10 characters long and if it contains a letter
            System.out.println("Wrong input try again");
            newPersonNumber = sc.next();
        }
        memberRegistry.changeMember(newName,newPersonNumber,memberID);
    }
    /*Display information about change boat process*/
    public void changeBoatRegistryView(String boatID) throws IOException {
        Boat boat = new Boat();
        String uppdatedType, uppdatedLength;
        Scanner scan = new Scanner(System.in);

        System.out.println("1 - Sailboat, 2 - Motorsailer, 3 - Kayak/Canoe, 4 - Other");
        System.out.print("Choose new type: ");
        uppdatedType = scan.next();
        try {
            boat.setBoatType(uppdatedType);
        } catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
            changeBoatRegistryView(boatID);
        }
        System.out.print("Set new length: ");
        uppdatedLength = scan.next();
        try {
            Integer.parseInt(uppdatedLength);
        } catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
            changeBoatRegistryView(boatID);
        }
        memberRegistry.changeBoat(boatID,boat.getBoatType(),uppdatedLength);
    }
    /*Returns true when memberID is already in file */
    public boolean verifyID(String memberID) throws IOException {
        if (memberRegistry.existInFile(memberID)) {
            return true;
        }
        else{
            System.out.print("No such member! Please try again: ");
            return false;
        }
    }
    /*Returns true when boatID is already in file */
    public boolean verifyBoatID(String boatID) throws IOException {
        if (memberRegistry.existInFile(boatID)) {
            return true;
        }
        else{
            System.out.print("No such boat! Please try again: ");
            return false;
        }
    }
    /*Returns true when input is already in file */
    public boolean personNumberAlreadyExist(String input) throws IOException {
        if (memberRegistry.existInFile(input)) {
            System.out.println("Person number: " + input + " already exist in database");
            return false;
        }
        else
            return true;
    }
}
