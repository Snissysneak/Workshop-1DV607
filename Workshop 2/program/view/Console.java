package view;

import model.Boat;
import model.Member;
import model.MemberRegistry;

import java.io.IOException;
import java.util.Scanner;

public class Console extends MemberRegistry {
    private final RegistryView c_registryView = new RegistryView();
    private MemberRegistry c_memberRegistry = new MemberRegistry();
    private final Member c_member = new Member();

    /*
     * This is the head menu where we make our first choices.
     * It leads to a another method that runs a sub menu.
     *
     * No need to look here for anything specific mostly
     * just class calls and if-statements.
     * */

    public void mainMenu() throws IOException {
        Scanner scanner = new Scanner(System.in); //Implement scanner

        System.out.println("Welcome to main menu"); //Start of menu
        System.out.println("1. Member\n2. Boat\n3. List");
        int choice = scanner.nextInt();
        subMenu(choice);
    }

    /*
     * This method will run a sub menu when it's called in mainMenu method.
     * In here we have several different if statements depending on the choices we make.
     * */

    public void subMenu(int choice) throws IOException {

        Scanner sc = new Scanner(System.in); //Implement scanner
        int val;

        //SubMenu Member
        if (choice == 1) {
            System.out.println("You're now in the member menu:"); //Start of menu
            System.out.println("1. Create Member\n2. Member Info\n3. Change member\n4. Remove member\n");
            val = sc.nextInt();

            //Create member
            if (val == 1) {
                System.out.print("Set name: ");
                String name = sc.next();
                System.out.print("Set personal number (10 numbers): ");
                String personalNum = sc.next();

                //Check if the input is 10 characters long and if it contains a letter
                while (personalNum.length() != 10 || !c_member.checkLetter(personalNum)) {
                    System.out.println("Wrong input try again");
                    personalNum = sc.next();
                }
                //Check if the input already exist in the file
                while (!c_registryView.personNumberAlreadyExist(personalNum)) {
                    personalNum = sc.next();
                }
                int memberID = c_member.idGenerator(); //call for rand id
                String memberIDstring = Integer.toString(memberID);
                while (c_memberRegistry.existInFile(memberIDstring)){
                    memberID = c_member.idGenerator(); //call for rand id
                    memberIDstring = Integer.toString(memberID);
                }
                System.out.println("Generated ID for this member: " +memberIDstring);

                c_memberRegistry.addMember(name, personalNum, memberID); //call for adding the member
                System.out.println("Member succesfully created");
            }
            //Member Info
            else if (val == 2) {
                System.out.print("Give id of the member you want to check: ");
                //Verify given ID
                String id = sc.next();
                while (!c_registryView.verifyID(id)) {
                    id = sc.next();
                }
                c_registryView.getMemberInfo(id);

            }
            //Change member
            else if (val == 3) {
                System.out.print("Give id of the member you want to change: ");
                String id = sc.next();
                //Verify given ID
                while (!c_registryView.verifyID(id)) {
                    id = sc.next();
                }
                c_registryView.changeMemberRegistryView(id);
                System.out.println("Member succesfully changed");

            }
            //Remove member
            else if (val == 4) {
                System.out.print("Give id of the member you want to delete: ");
                String id = sc.next();
                //Verify given ID
                while (!c_registryView.verifyID(id)) {
                    id = sc.next();
                }
                c_memberRegistry.deleteMember(id);
                System.out.println("Member removed!");
            }
            //Invalid input
            else {
                System.out.println("This value is not valid");
            }


        }
        //SubMenu Boat
        else if (choice == 2) {
            System.out.println("1. Register Boat\n2. Boat Info\n3. Change Boat\n4. Remove boat");
            val = sc.nextInt();

            //Register Boat
            if (val == 1) {
                Boat boat = new Boat();
                System.out.println("1 - Sailboat, 2 - Motorsailer, 3 - Kayak/Canoe, 4 - Other");
                System.out.print("Choose type: ");
                String type = sc.next();
                try {
                    boat.setBoatType(type);
                } catch (NumberFormatException ex) {
                    System.out.println("\nWrong input! Please try again");
                    subMenu(2);
                }
                System.out.println("Enter boat length");
                String input = sc.next();
                //Check if length is Integer
                try {
                    int length = Integer.parseInt(input);
                    boat.setBoatLength(length);
                } catch (NumberFormatException ex) {
                    System.out.println("\nWrong input! Please try again");
                    subMenu(2);
                }
                System.out.println("To what member do you want to add the boat to?");
                String owner = sc.next();

                int boatID = c_member.idGenerator();
                String boatIDstring = Integer.toString(boatID);
                //Check if the input already exist in the file
                while (c_memberRegistry.existInFile(boatIDstring)){
                    boatID = c_member.idGenerator(); //call for rand id
                    boatIDstring = Integer.toString(boatID);
                }
                System.out.println("Generated ID for this boat: " +boatIDstring);

                c_memberRegistry.addBoat(boatID,boat.getBoatType(), boat.getBoatLength(), owner);
            }
            //Boat info
            else if (val == 2) {
                System.out.print("Give id of the boat you want to check: ");
                String id = sc.next();
                //Verify given ID
                while (!c_registryView.verifyBoatID(id)) {
                    id = sc.next();
                }
                c_registryView.getBoatInfo(id);

            }
            //Change boat
            else if (val == 3) {
                System.out.print("Give id of the boat you want to change: ");
                String id = sc.next();
                //Verify given ID
                while (!c_registryView.verifyBoatID(id)) {
                    id = sc.next();
                }
                c_registryView.changeBoatRegistryView(id);
                System.out.println("Boat succesfully changed");
            }
            //Remove boat
            else if (val == 4) {
                System.out.println("What boat do you want to remove? (enter boat ID)");
                String id = sc.next();
                //Verify given ID
                while (!c_registryView.verifyBoatID(id)) {
                    id = sc.next();
                }
                c_memberRegistry.deleteBoat(id);
            }
            //Invalid input
            else {
                System.out.println("This value is not valid");
            }
        }
        //SubMenu List
        else if (choice == 3) { //Start of menu
            System.out.println("You're now in the list menu:");
            System.out.println("1. Get verbose list\n2. Get compose list\n");
            val = sc.nextInt();

            //Verbose list
            if (val == 1) {
                System.out.println("Verbose list of the club members:");
                c_registryView.getVerboseList();
            }
            //Compose list
            else if (val == 2) {
                System.out.println("Compose list of the club members:");
                c_registryView.getComposeList();
            }
            //Invalid input
            else {
                System.out.println("This value is not valid");
            }
        }
        //Invalid input in MainMenu
        else {
            System.out.println("This value is not valid");
        }

        //Asks if you want to return to main menu.
        System.out.println("\nType 1 to go back to main menu?");
        int value = sc.nextInt();
        if (value == 1) {
            mainMenu();
        }

    }
    /*Main method to start a program*/
    public static void main(String[] args) throws IOException {
        Console console = new Console();
        console.mainMenu(); //Starts off the program by calling the method mainMenu.
    }
}
