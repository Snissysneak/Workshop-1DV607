package view;
import model.Boat;
import model.MemberRegistry;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RegistryView {
    MemberRegistry memberRegistry = new MemberRegistry();
    public void getMemberInfo(String input) throws IOException {

        File file = new File("members.txt");


        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String [] entry = line.split(",");
            if (entry[0].equals(input)) {
                printComposeEntry(entry);
            }
        }
        scan.close();

    }
    public void getComposeList() throws IOException {

        File file = new File("members.txt");
        Scanner scan = new Scanner(file);

        System.out.println("Compose list of members:");
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String [] entry = line.split(",");
            printComposeEntry(entry);
        }
        scan.close();
    }
    public void getVerboseList() {
        System.out.println("Comming soon!");

    }
    public void printComposeEntry(String[] cEntry){
        System.out.print("Member ID: " +cEntry[0]);
        System.out.print(", name: " +cEntry[1]);
        System.out.print(", person number: " +cEntry[2] +"\n");
    }
    public void printVerboseEntry(String vEntry){
        System.out.println("Comming soon!");
    }

    public void changeMemberRegistryView(String input_id) throws IOException {
        String uppdatedName, uppdatedPersonalNumber;
        Scanner scan = new Scanner(System.in);

        System.out.print("Set new name: ");
        uppdatedName = scan.next();
        System.out.print("Set new personal number: ");
        uppdatedPersonalNumber = scan.next();

        memberRegistry.updateRegistry(input_id,uppdatedName,uppdatedPersonalNumber);
    }
    public void changeBoatRegistryView(String input_id) throws IOException {
        Boat boat = new Boat();

        String uppdatedType, uppdatedLength;

        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Sailboat, 2 - Motorsailer, 3 - Kayak/Canoe, 4 - Other");
        System.out.print("Choose new type: ");
        uppdatedType = sc.next();
        try{
            boat.setBoatType(uppdatedType);
        }catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
            changeBoatRegistryView(input_id);
        }
        System.out.print("Set new length: ");
        uppdatedLength = sc.next();
        try{
            Integer.parseInt(uppdatedLength);
        }catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
            changeBoatRegistryView(input_id);
        }
        memberRegistry.updateRegistry(input_id,uppdatedType,uppdatedLength);
        sc.close();
    }

}
