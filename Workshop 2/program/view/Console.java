package view;

import model.Member;
import model.MemberRegistry;

import java.io.IOException;
import java.util.Scanner;

public class Console extends MemberRegistry {
    /*
    * This is the head menu where we make our first choices.
    * It leads to a another method that runs a sub menu.
    * */

    static void mainMenu() throws IOException {
        Scanner sc = new Scanner(System.in); //Implement scanner

        System.out.println("Welcome to main menu"); //Start of menu
        System.out.println("1. Member\n2. Boat\n3. List");
        int choice = sc.nextInt();
        subMenu(choice);
    }

    /*
    * This method will run a sub menu when it's called in mainMenu method.
    * In here we have several different if statements depending on the choices we make.
    * */

    static void subMenu(int choice) throws IOException {
        MemberRegistry memReg = new MemberRegistry();
        Member mem = new Member();
        Scanner sc = new Scanner(System.in); //Implement scanner
        int val;

        if (choice == 1) {
            System.out.println("You're now in the member menu:"); //Start of menu
            System.out.println("1. Create Member\n2. Member Info\n3. Change member\n4. Remove member\n");
            val = sc.nextInt();

            //Here we make our choices for member

            if (val == 1) {
                System.out.print("Set name: ");
                String name = sc.next();
                System.out.print("Set personal number: ");
                String personalNum = sc.next();
                int memberID = mem.idGenerator(); //call for rand id

                memReg.addMember(name, personalNum, memberID); //call for adding the member
                System.out.println("Member succesfully created");
            }
            else if (val == 2) {
                System.out.print("Give id of the member you want to check: ");
                String id = sc.next();
                memReg.getMemberInfo(id);
            }
            else if (val == 3) {
                System.out.print("Chose member id for the member you want to change: ");
                String id = sc.next();
                memReg.changeMember(id);

            }
            else if (val == 4) {
                System.out.print("Give id of the member you want to delete: ");
                String id = sc.next();
                memReg.deleteMember(id);
                System.out.println("Member removed!");
            }
            else {
                System.out.println("This value is not valid");
            }
        }
        else if (choice == 2) {
            System.out.println("You're now in the boat menu:"); //Start of menu
            System.out.println("1. Register Boat\n2. Boat Info\n3. Change Boat\n4. Remove boat");
            val = sc.nextInt();

            //Here we make our choices for boat

            if (val == 1) {
                System.out.println("Register boat!");
            }
            else if (val == 2) {
                System.out.println("Boat info!");
            }
            else if (val == 3) {
                System.out.println("Change boat!");
            }
            else if (val == 4) {
                System.out.println("Remove boat!");
            }
            else {
                System.out.println("This value is not valid");
            }
        }
        else if (choice == 3) { //Start of menu
            System.out.println("You're now in the list menu:");
            System.out.println("1. Get verbose list\n2. Get compose list\n");
            val = sc.nextInt();

            //Here we make our choices for lists

            if (val == 1) {
                System.out.println("Get verbose!");
            }
            else if (val == 2) {
                System.out.println("Get compose!");
                memReg.getComposeList();
            }

            else {
                System.out.println("This value is not valid");
            }
        }
        else {
            System.out.println("This value is not valid");
        }

        //Asks if you want to return to main menu.

        System.out.println();
        System.out.println("Do you want to go back to main menu? Type 1 for yes and 2 for no");
        int value = sc.nextInt();
        if (value == 1) {
            mainMenu();
        }
        else if (value == 2) {
            sc.close();
        }
        else {
            System.out.println("This value is not valid");
        }
    }
    public void printComposeEntry(String[] cEntry){
        System.out.print("Member ID: " +cEntry[0]);
        System.out.print(", name: " +cEntry[1]);
        System.out.print(", person number: " +cEntry[2] +"\n");
    }

    public static void main (String[] args) throws IOException {
        mainMenu(); //Starts off the program by calling the method mainMenu.
    }
}