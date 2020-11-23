package view;

import model.Boat;
import model.MemberRegistry;

import java.io.IOException;
import java.util.Scanner;

public class Console extends MemberRegistry {
    private final RegistryView registryView = new RegistryView();


    /*Main method to start a program*/
    public static void main(String[] args) throws IOException {
        Console console = new Console();
        console.mainMenu(); //Starts off the program by calling the method mainMenu.
    }

    public void mainMenu() throws IOException {
        registryView.rv_memberRegistry.readFile();
        System.out.println("Welcome in Boat Club"); //Start of menu
        System.out.println("1. Member\n2. Boat\n3. List\n4. Quit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();


        if (choice == 1)
            memberSubMenu();

        if (choice == 2)
            boatSubMenu();

        if (choice == 3)
            listSubMenu();

        if (choice == 4)
            System.exit(1);

        else
            //invalidInput();
            registryView.rv_memberRegistry.readFile();
    }

    public void memberSubMenu() throws IOException {
        System.out.println("You're now in the member menu:");
        System.out.println("1. Create Member\n2. Member Info\n3. Change member\n4. Remove member\n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        //Create member
        if (choice == 1) {
            createMemberView();
            wantToContinue();
        }

        //Member Info
        else if (choice == 2) {
            memberInfoView();
            wantToContinue();
        }

        //Change member
        else if (choice == 3) {
            changeMemberView();
            wantToContinue();
        }

        //Remove member
        else if (choice == 4) {
            deleteMemberView();
            wantToContinue();
        }

        //Invalid input
        else
            invalidInput();
        sc.close();
    }

    public void boatSubMenu() throws IOException {
        System.out.println("You're now in the boat menu:");
        System.out.println("1. Register Boat\n2. Boat Info\n3. Change Boat\n4. Remove boat");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        //Register Boat
        if (choice == 1)
            registerBoatView();

            //Boat info
        else if (choice == 2)
            boatInfoView();

            //Change boat
        else if (choice == 3)
            changeBoatView();

            //Remove boat
        else if (choice == 4) {
            System.out.print("What boat do you want to remove? (enter boat ID): ");
            int id = sc.nextInt();
            //Verify given ID
            while (registryView.verifyBoatID(id)) {
                id = sc.nextInt();
            }
            registryView.rv_memberRegistry.deleteBoat(id);
        } else
            invalidInput();
        sc.close();
    }

    public void listSubMenu() throws IOException {
        System.out.println("You're now in the list menu:");
        System.out.println("1. Get verbose list\n2. Get compose list\n");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        //Verbose list
        if (choice == 1) {
            System.out.println("Verbose list of the club members:");
            registryView.getVerboseList();
            wantToContinue();
        }
        //Compose list
        else if (choice == 2) {
            System.out.println("Compose list of the club members:");
            registryView.getComposeList();
            wantToContinue();

        }
        //Invalid input
        else
            invalidInput();

        mainMenu();
        sc.close();
    }

    public void createMemberView(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Set name: ");
        registryView.rv_member.setName(sc.next());

        System.out.print("Set personal number (10 numbers): ");
        String v_personalNum = sc.next();
        registryView.personalNumberCheck(v_personalNum);
        registryView.rv_member.setPersonalNum(v_personalNum);

        int v_memberID = registryView.rv_memberRegistry.idGenerator(); //call for rand id
        registryView.rv_member.setMemberID(v_memberID);
        System.out.println("Generated ID for this member: " + v_memberID);

        registryView.rv_memberRegistry.addMember(registryView.rv_member); //call for adding the member
        System.out.println("Member succesfully created");
        wantToContinue();
        sc.close();
    }

    public void memberInfoView() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Give id of the member you want to check: ");
        //Verify given ID
        int id = sc.nextInt();

        while (registryView.existMemberView(id)) {
            id = sc.nextInt();
        }
        registryView.getMemberInfo(id);
        wantToContinue();
        sc.close();
    }

    public void changeMemberView() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Give id of the member you want to change: ");
        int id = sc.nextInt();
        //Verify given ID
        while (registryView.existMemberView(id)) {
            id = sc.nextInt();
        }
        registryView.changeMemberRegistryView(id);
        System.out.println("Member succesfully changed");
        sc.close();
    }

    public void deleteMemberView() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Give id of the member you want to delete: ");
        int id = sc.nextInt();
        //Verify given ID
        while (registryView.existMemberView(id)) {
            id = sc.nextInt();
        }
        registryView.rv_member = registryView.rv_memberRegistry.getMember(id);
        registryView.rv_memberRegistry.deleteMember(registryView.rv_member);
        System.out.println("Member removed!");
        sc.close();
    }

    public void registerBoatView() throws IOException {
        Scanner sc = new Scanner(System.in);

        Boat v_boat = new Boat();
        System.out.println("1 - Sailboat, 2 - Motorsailer, 3 - Kayak/Canoe, 4 - Other");
        System.out.print("Choose type: ");
        int type = sc.nextInt();

        Boat.Type v_boatType = registryView.boatTypeView(type);
        v_boat.setBoatType(v_boatType);

        System.out.print("Enter boat length: ");
        String input = sc.next();
        //Check if length is Integer
        try {
            int length = Integer.parseInt(input);
            v_boat.setBoatLength(length);
        } catch (NumberFormatException ex) {
            System.out.println("\nWrong input! Please try again");
        }
        System.out.print("To what member do you want to add the boat to? (enter Member ID: ");
        //Verify given ID
        int v_ownerID = sc.nextInt();
        while (registryView.existMemberView(v_ownerID)) {
            v_ownerID = sc.nextInt();
        }

        int v_boatID = registryView.rv_memberRegistry.idGenerator();
        while (registryView.rv_memberRegistry.existInFile(v_boatID)) {
            v_boatID = registryView.rv_memberRegistry.idGenerator(); //call for rand id
        }
        v_boat.setBoatID(v_boatID);
        System.out.println("Generated ID for this boat: " + v_boatID);

        registryView.rv_memberRegistry.addBoat(v_ownerID, v_boat);
        sc.close();
    }

    public void boatInfoView() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Give id of the boat you want to check: ");
        int id = sc.nextInt();
        //Verify given ID
        while (registryView.verifyBoatID(id)) {
            id = sc.nextInt();
        }
        registryView.getBoatInfo(id);
        sc.close();
    }

    public void changeBoatView() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Give id of the boat you want to change: ");
        int id = sc.nextInt();
        //Verify given ID
        while (registryView.verifyBoatID(id)) {
            id = sc.nextInt();
        }
        registryView.changeBoatRegistryView(id);
        System.out.println("Boat succesfully changed");
        sc.close();
    }

    public void invalidInput() throws IOException {
        System.out.println("This value is invalid. ");
        System.out.print("Please try again or press 1 to go back to main menu");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        if (choice == 1)
            mainMenu();
        sc.close();
    }

    //TODO
    public void wantToContinue() {
        System.exit(1);
    }

}
