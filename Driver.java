import java.util.*;
import java.io.*;

public class Driver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean flag = false;
        String filePath = null;
        File file = null;

        do {
            System.out.printf("\nInput file path: ");
            filePath = input.nextLine();
            file = new File(filePath);
            flag = false;
            if (!file.exists()) {
                System.out.printf("\nFILE DOES NOT EXIST\n");
                flag=true;
            }
        } while(flag);

        Network netwk = new Network(file);
        System.out.printf("\nGraph loaded!");

        flag=true;
        int choice=-1;
        do {
            System.out.printf("\n\nMAIN MENU\n[1] Get friend list\n[2] Get connection\n[3] Exit");
            try {
                System.out.printf("\nEnter your choice: ");
                choice = input.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.printf("\nERROR\n");
                input.nextLine();
            }

            switch (choice) {
                case 1: netwk.displayFriends(); break;
                case 2: ArrayList<Integer> path = netwk.findPath(); 
                if (path!=null)
                {
                    for (int i = path.size()-1; i>0; i--) {
                        System.out.printf("\n%d is friends with %d", path.get(i), path.get(i-1));
                    }
                }
                break;
                case 3: flag = false; break;
                default: System.out.printf("\nINVALID\n");break;
            }
        } while(flag);
    }
}