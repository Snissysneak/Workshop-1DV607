package view;
import model.Boat;
import model.Member;
import model.MemberRegistry;

import java.util.Scanner;

public class RegistryView {
    protected MemberRegistry rv_memberRegistry = new MemberRegistry();
    protected Member rv_member = new Member();


    /*Check if record with given member exist and send data to printMember()*/
    public void getMemberInfo(int memberID) {
        rv_memberRegistry.readFile();
        rv_member = rv_memberRegistry.getMember(memberID);
        printMember(rv_member);
    }
    /*Check if given boat exist and send data to printBoat()*/
    public void getBoatInfo(int a_boatID) {
        rv_memberRegistry.readFile();
        for (Member m : rv_memberRegistry.members_list)
            for(Boat b : m.ownedBoats)
                if(b.getBoatID() == a_boatID)
                    printBoat(b);
    }
    /*Display information about change member process*/
    public void changeMemberRegistryView(int a_memberID){
        rv_memberRegistry.readFile();
        Scanner sc  = new Scanner(System.in);

        rv_member = rv_memberRegistry.getMember(a_memberID);
        rv_member.setMemberID(a_memberID);

        System.out.print("Set new name: ");
        rv_member.setName(sc.next());

        System.out.print("Set new personal number: ");
        String newPersonNumber = sc.next();
        personalNumberCheck(newPersonNumber);
        rv_member.setPersonalNum(newPersonNumber);

        rv_memberRegistry.changeMember(rv_member);
    }
    /*Display information about change boat process*/
    public void changeBoatRegistryView(int boatID){
        Boat rv_boat = new Boat();
        rv_memberRegistry.readFile();
        rv_boat.setBoatID(boatID);

        Scanner scan = new Scanner(System.in);

        System.out.println("1 - Sailboat, 2 - Motorsailer, 3 - Kayak/Canoe, 4 - Other");
        System.out.print("Choose new type: ");
        try {
            Boat.Type rv_boatType = boatTypeView(scan.nextInt());
            rv_boat.setBoatType(rv_boatType);

        } catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
            changeBoatRegistryView(boatID);
        }
        System.out.print("Set new length: ");
        try {
            int uppdatedLength = scan.nextInt();
            rv_boat.setBoatLength(uppdatedLength);
        } catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
            changeBoatRegistryView(boatID);
        }
        rv_memberRegistry.changeBoat(rv_boat);
    }

    /*Method prints compose list of members */
    public void getComposeList() {
        rv_memberRegistry.readFile();
        for (Member m : rv_memberRegistry.members_list)
            printComposeEntry(m);
    }

    /* Print one record of compose list in given record */
    public void printComposeEntry(Member a_member){
        int boats = rv_memberRegistry.getNumberOfBoats(a_member);

        System.out.print("Member ID: " + a_member.getMemberID());
        System.out.print(", name: " + a_member.getName());
        System.out.print(", owned boats: " + boats + "\n");
    }

    /* Print verbose list with club members */
    public void getVerboseList() {

        for (Member m : rv_memberRegistry.members_list){
            printMember(m);
            System.out.println("Owned boats: ");
            for (Boat b : m.ownedBoats){
                printBoat(b);
            }
            System.out.println();
        }
    }
    /* Print information about member in given record */
    public void printMember(Member a_member){
        System.out.print("Member ID: " + a_member.getMemberID());
        System.out.print(", name: " + a_member.getName());
        System.out.print(", person number: " + a_member.getPersonalNum() + "\n");
    }
    /*Print information about given Boat */
    public void printBoat(Boat a_boat) {
        System.out.println("BoatID: " + a_boat.getBoatID()
                + ", boat type: " + a_boat.getBoatType()
                + ", boat length: " + a_boat.getBoatLength());
    }
    /*Returns true when memberID is already in file */
    public boolean existMemberView(int a_memberID) {
        if (rv_memberRegistry.notExistInFile(a_memberID)) {
            System.out.print("No such member! Please try again: ");
        }
        return rv_memberRegistry.notExistInFile(a_memberID);
    }
    /*Returns true when boatID is already in file */
    public boolean verifyBoatID(int boatID){
        if (rv_memberRegistry.notExistInFile(boatID)) {
            System.out.print("No such boat! Please try again: ");
        }
        return rv_memberRegistry.notExistInFile(boatID);
    }
    public Boat.Type boatTypeView(int a_type){ //Check this later
        Boat.Type rv_type = null;
        try {
            if (a_type == 1)
                rv_type = Boat.Type.Sailboat;
            else if(a_type == 2)
                rv_type = Boat.Type.Motorsailer;
            else if(a_type == 3)
                rv_type = Boat.Type.Kayak_Canoe;
            else if(a_type == 4)
                rv_type = Boat.Type.Other;
        } catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
        }
        return rv_type;
    }
    public void personalNumberCheck(String a_pn) {
        Scanner sc = new Scanner(System.in);

        //Check if the input is 10 characters long and if it contains a letter
        while (!rv_memberRegistry.checkPersonalNumber(a_pn)) {
            System.out.println("Wrong input try again");
            a_pn = sc.next();
        }

        //Check if the input already exist in the file
        while (!rv_memberRegistry.notExistInFile(Integer.parseInt(a_pn))) {
            System.out.println("Person number: " + a_pn + " already exist in database");
            a_pn = sc.next();
        }
        sc.close();
    }
}
