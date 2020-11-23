package view;
import model.MemberRegistry;

import java.io.IOException;
import java.util.Scanner;

public class Console extends MemberRegistry {
    private final RegistryView registryView = new RegistryView();
    private final static Scanner sc = new Scanner(System.in);

    public void mainMenu() throws IOException {
        registryView.rv_memberRegistry.readFile();
        System.out.println("Welcome in Boat Club"); //Start of menu
        System.out.println("1. Member\n2. Boat\n3. List\n4. Quit");
        int choice = sc.nextInt();

        if (choice == 1)
            memberSubMenu();

        else if (choice == 2)
            boatSubMenu();

        else if  (choice == 3)
            listSubMenu();

        else if  (choice == 4)
            System.exit(1);

        else {
            invalidInput();
        }
    }
    public void memberSubMenu() throws IOException {
        System.out.println("You're now in the member menu:");
        System.out.println("1. Create Member\n2. Member Info\n3. Change member\n4. Remove member\n");
        int choice = sc.nextInt();

        //Create member
        if (choice == 1) {
            registryView.createMemberView();
        }
        //Member Info
        else if (choice == 2) {
            registryView.getMemberInfoView();
        }
        //Change member
        else if (choice == 3) {
            registryView.changeMemberView();
        }
        //Remove member
        else if (choice == 4) {
            registryView.deleteMemberView();
        }
        //Invalid input
        else
            invalidInput();
    }

    public void boatSubMenu() throws IOException {
        System.out.println("You're now in the boat menu:");
        System.out.println("1. Register Boat\n2. Boat Info\n3. Change Boat\n4. Remove boat");
        int choice = sc.nextInt();

        //Register Boat
        if (choice == 1)
            registryView.registerBoatView();

            //Boat info
        else if (choice == 2)
            registryView.getBoatInfo();

            //Change boat
        else if (choice == 3)
            registryView.changeBoatRegistryView();

            //Remove boat
        else if (choice == 4) {
            registryView.deleteBoatView();
        }
        else
            invalidInput();
    }

    public void listSubMenu() throws IOException {
        System.out.println("You're now in the list menu:");
        System.out.println("1. Get verbose list\n2. Get compose list\n");
        int choice = sc.nextInt();

        //Verbose list
        if (choice == 1) {
            System.out.println("Verbose list of the club members:");
            registryView.getVerboseList();
            mainMenu();
        }
        //Compose list
        else if (choice == 2) {
            System.out.println("Compose list of the club members:");
            registryView.getComposeList();
            mainMenu();

        }
        //Invalid input
        else
            invalidInput();
    }

    public void invalidInput() throws IOException {
        System.out.println("This value is invalid. ");
        mainMenu();
    }

}

