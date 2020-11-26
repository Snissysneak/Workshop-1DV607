package view;
import model.Boat;
import model.Member;
import model.MemberRegistry;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistryView {

    protected MemberRegistry memberRegistry = new MemberRegistry();
    private final static Scanner sc = new Scanner(System.in);

    //Member

    /* Create member process with instructions */
    public void createMemberView() {
        Member rv_member = new Member();
        System.out.print("Set name: ");
        rv_member.setName(sc.next());
        System.out.print("Set personal number (10 numbers): ");
        BigInteger v_personalNum = sc.nextBigInteger();
        personalNumberCheck(v_personalNum);
        rv_member.setPersonalNum(v_personalNum);
        int v_memberID = memberRegistry.idGenerator(); //call for rand id
        rv_member.setMemberID(v_memberID);
        System.out.println("Generated ID for this member: " + v_memberID);
        memberRegistry.addMember(rv_member); //call for adding the member
        System.out.println("Member succesfully created");
    }

    /* Member info process with instructions */
    public void getMemberInfoView() {
        System.out.print("Give id of the member you want to check: ");
        int id = sc.nextInt();
        while (existMemberView(id)) {
            id = sc.nextInt();
        }
        memberRegistry.readFile();
        Member rv_member = memberRegistry.getMember(id);
        printMember(rv_member);
    }

    /* Change member process with instructions */
    public void changeMemberView() {
        System.out.print("Give id of the member you want to change: ");
        int id = sc.nextInt();
        while (existMemberView(id)) {
            id = sc.nextInt();
        }
        memberRegistry.readFile();
        Member rv_member = memberRegistry.getMember(id);
        rv_member.setMemberID(id);
        System.out.print("Set new name: ");
        rv_member.setName(sc.next());
        System.out.print("Set new personal number: ");
        BigInteger newPersonNumber = sc.nextBigInteger();
        personalNumberCheck(newPersonNumber);
        rv_member.setPersonalNum(newPersonNumber);
        memberRegistry.changeMember(rv_member);
        System.out.println("Member succesfully changed");
    }

    /* Delete member process with instructions */
    public void deleteMemberView() {
        Member rv_member;
        System.out.print("Give id of the member you want to delete: ");
        int id = sc.nextInt();
        while (existMemberView(id)) {
            id = sc.nextInt();
        }
        rv_member = memberRegistry.getMember(id);
        memberRegistry.deleteMember(rv_member);
        System.out.println("Member removed!");
    }

    /* Returns true when memberID is already in file */
    public boolean existMemberView(int a_memberID) {
        if (!memberRegistry.idExistInFile(a_memberID)) {
            System.out.print("No such member! Please try again: ");
        }
        return !memberRegistry.idExistInFile(a_memberID);
    }
    /* Method checks person number */
    public void personalNumberCheck(BigInteger a_pn) {
        while (!memberRegistry.checkPersonalNumber(a_pn)) {
            System.out.println("Wrong input try again");
            a_pn = sc.nextBigInteger();
        }
        while (memberRegistry.personNumberExistInFile(a_pn)) {
            System.out.println("Person number: " + a_pn + " already exist in database");
            System.out.print("Please try again: ");
            a_pn = sc.nextBigInteger();
        }
    }
    //
    //Boat
    //
    public void registerBoatView(){
        Boat v_boat = new Boat();
        System.out.println("1 - Sailboat, 2 - Motorsailer, 3 - Kayak/Canoe, 4 - Other");
        System.out.print("Choose type: ");
        int type = sc.nextInt();
        Boat.Type v_boatType = boatTypeView(type);
        v_boat.setBoatType(v_boatType);
        System.out.print("Enter boat length: ");
        String input = sc.next();

        try {
            int length = Integer.parseInt(input);
            v_boat.setBoatLength(length);
        } catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
        }
        System.out.print("To what member do you want to add the boat to? (enter Member ID: ");
        //Verify given ID
        int v_ownerID = sc.nextInt();
        while (existMemberView(v_ownerID)) {
            v_ownerID = sc.nextInt();
        }

        int v_boatID = memberRegistry.idGenerator();
        while (memberRegistry.idExistInFile(v_boatID)) {
            v_boatID = memberRegistry.idGenerator(); //call for rand id
        }
        v_boat.setBoatID(v_boatID);
        System.out.println("Generated ID for this boat: " + v_boatID);

        memberRegistry.addBoat(v_ownerID, v_boat);

    }

    /* Boat info process with instructions */
    public void getBoatInfo() {
        ArrayList<Member> members = memberRegistry.getMembersList();
        memberRegistry.readFile();
        System.out.print("Give id of the boat you want to check: ");
        int id = sc.nextInt();
        //Verify given ID
        while (verifyBoatID(id)) {
            id = sc.nextInt();
        }

        for (Member m : members)
            for(Boat b : m.ownedBoats)
                if(b.getBoatID() == id)
                    printBoat(b);
    }
    /* Change boat process with instructions */
    public void changeBoatRegistryView(){
        Boat rv_boat = new Boat();
        memberRegistry.readFile();

        System.out.print("Give id of the boat you want to change: ");
        int id = sc.nextInt();
        rv_boat.setBoatID(id);

        while (verifyBoatID(id)) {
            id = sc.nextInt();
        }
        System.out.println("1 - Sailboat, 2 - Motorsailer, 3 - Kayak/Canoe, 4 - Other");
        System.out.print("Choose new type: ");
        try {
            Boat.Type rv_boatType = boatTypeView(sc.nextInt());
            rv_boat.setBoatType(rv_boatType);

        } catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
            changeBoatRegistryView();
        }
        System.out.print("Set new length: ");
        try {
            int uppdatedLength = sc.nextInt();
            rv_boat.setBoatLength(uppdatedLength);
        } catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
            changeBoatRegistryView();
        }
        memberRegistry.changeBoat(rv_boat);
        System.out.println("Boat succesfully changed");
    }
    /* Delete boat process with instructions */
    public void deleteBoatView(){
        System.out.print("What boat do you want to remove? (enter boat ID): ");
        int id = sc.nextInt();
        //Verify given ID
        while (verifyBoatID(id)) {
            id = sc.nextInt();
        }
        memberRegistry.deleteBoat(id);
        System.out.println("Boat succesfully removed");
    }

    /*Returns true when boatID is already in file */
    public boolean verifyBoatID(int boatID){
        if (!memberRegistry.idExistInFile(boatID)) {
            System.out.print("No such boat! Please try again: ");
        }
        return !memberRegistry.idExistInFile(boatID);
    }
    /* Method "translate" input to boat  type */
    public Boat.Type boatTypeView(int a_type){
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
    //
    //List / Print
    //

    /*Method prints compose list of members */
    public void getComposeList() {
        ArrayList<Member> members = memberRegistry.getMembersList();
        memberRegistry.readFile();
        for (Member m : members)
            printComposeEntry(m);
    }

    /* Print one record of compose list in given record */
    public void printComposeEntry(Member a_member){
        int boats = memberRegistry.getNumberOfBoats(a_member);

        System.out.print("Member ID: " + a_member.getMemberID());
        System.out.print(", name: " + a_member.getName());
        System.out.print(", owned boats: " + boats + "\n");
    }

    /* Print verbose list with club members */
    public void getVerboseList() {
        ArrayList<Member> members = memberRegistry.getMembersList();
        for (Member m : members){
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
}
