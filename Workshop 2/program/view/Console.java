package view;
import model.Boat;
import model.Member;
import model.MemberRegistry;
import java.io.IOException;
import java.util.Scanner;

public class Console extends MemberRegistry {
    /*
    * This is the head menu where we make our first choices.
    * It leads to a another method that runs a sub menu.
    *
    * No need to look here for anything specific mostly
    * just class calls and if-statements.
    * */

    static void mainMenu() throws IOException {
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

    static void subMenu(int choice) throws IOException {
        MemberRegistry memReg = new MemberRegistry();
        RegistryView list = new RegistryView();
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
                System.out.print("Set personal number (10 numbers): ");
                String personalNum = sc.next();
                while (personalNum.length() != 10 || !mem.checkLetter(personalNum)) { //Check if the input is 10 characters long and if it contains a letter
                    System.out.println("Wrong input try again");
                    personalNum = sc.next();
                }
                int memberID = mem.idGenerator(); //call for rand id

                memReg.addMember(name, personalNum, memberID); //call for adding the member
                System.out.println("Member succesfully created");
            }
            else if (val == 2) {
                System.out.print("Give id of the member you want to check: ");
                String id = sc.next();
                list.getMemberInfo(id);
            }
            else if (val == 3) {
                System.out.print("Give id of the member you want to change: ");
                String id = sc.next();
                list.changeMemberRegistryView(id);
                System.out.println("Member succesfully changed");

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
            System.out.println("1. Register Boat\n2. Boat Info\n3. Change Boat\n4. Remove boat");
            val = sc.nextInt();

            //Here we make our choices for boat
            if (val == 1) {
                Boat boat = new Boat();
                System.out.println("1 - Sailboat, 2 - Motorsailer, 3 - Kayak/Canoe, 4 - Other");
                System.out.print("Choose type: ");
                String type = sc.next();
                try{
                    boat.setBoatType(type);
                }catch (NumberFormatException ex) {
                    System.out.println("\nWrong input! Please try again");
                    subMenu(2);
                }

                System.out.println("Enter boat length");
                String input = sc.next();
                try{
                    int length = Integer.parseInt(input);
                    boat.setBoatLength(length);
                }catch (NumberFormatException ex) {
                    System.out.println("\nWrong input! Please try again");
                    subMenu(2);
                }
                System.out.println("To what member do you want to add the boat to?");
                String owner = sc.next();

                memReg.addBoat(boat.getBoatType(), boat.getBoatLength(), owner);
            }
            else if (val == 2) {
                System.out.println("Boat info!");
            }
            else if (val == 3) {
                System.out.print("Give id of the boat you want to change: ");
                String id = sc.next();
                list.changeBoatRegistryView(id);
                System.out.println("Boat succesfully changed");
            }
            else if (val == 4) {
                System.out.println("What boat do you want ot remove? (enter boat ID)");
                String id = sc.next();
                memReg.deleteBoat(id);
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
                list.getVerboseList();
            }
            else if (val == 2) {
                System.out.println("Get compose!");
                list.getComposeList();
            }

            else {
                System.out.println("This value is not valid");
            }
        }
        else {
            System.out.println("This value is not valid");
        }

        //Asks if you want to return to main menu.
        System.out.println("\nDo you want to go back to main menu? Type 1 for yes and 2 for no");
        int value = sc.nextInt();
        if (value == 1) {
            mainMenu();
        }
        else if (value == 2) {

        }
        else {
            System.out.println("This value is not valid");
        }

    }
    public static void main (String[] args) throws IOException {
        mainMenu(); //Starts off the program by calling the method mainMenu.
    }
}
