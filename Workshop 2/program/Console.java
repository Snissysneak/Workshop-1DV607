import java.util.Scanner;

public class Console {
    static void mainMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to main menu");
        System.out.println("1. Member\n2. Boat\n3. List");
        int choice = sc.nextInt();
        subMenu(choice);
    }

    static void subMenu(int choice) {
        Scanner sc = new Scanner(System.in);
        if (choice == 1) {
            System.out.println("You're now in the member menu:");
            System.out.println("1. Create Member\n2. Member Info\n3. Change member\n4. Remove member\n");
        }
        else if (choice == 2) {
            System.out.println("You're now in the boat menu:");
            System.out.println("1. Register Boat\n2. Boat Info\n3. Change Boat\n4. Remove boat");
        }
        else if (choice == 3) {
            System.out.println("You're now in the list menu:");
            System.out.println("1. Get verbose list\n2. Get compose list\n");
        }
        else {
            System.out.println("This value is not valid");
        }
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
    public static void main (String[] args) {
        mainMenu();
    }
}
