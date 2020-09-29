package view;
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
    public void changeMember(String input_id){
        String rv_name, rv_personalNumber;
        Scanner scan = new Scanner(System.in);
        System.out.print("Set new name: ");
        rv_name = scan.next();
        System.out.print("Set new personal number: ");
        rv_personalNumber = scan.next();
        memberRegistry.updateRegistry(input_id,rv_name,rv_personalNumber);
    }
}
