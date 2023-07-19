import java.util.*;
import java.io.*;

public class Network {
    private int noAccs;
    private int noFriendships;
    private boolean graph[][];

    public Network (File netwk) 
    {
        Scanner scanner = null;
        try {
            scanner = new Scanner(netwk);
        } catch (FileNotFoundException e) {
            System.out.println("error");
        }

        int acc1, acc2, 
            noAccs = scanner.nextInt(), 
            noFriendships = scanner.nextInt();

        this.noAccs=noAccs;
        this.noFriendships=noFriendships;  
        this.graph = new boolean[noAccs][noAccs];

        for(int i=0; i < noFriendships; i++) {
            acc1 = scanner.nextInt();
            acc2 = scanner.nextInt();
            this.graph[acc1][acc2] = true;
            this.graph[acc2][acc1] = true;
        }
        scanner.close();
    }

    public void displayFriends() 
    {
        int idNum = 0;
        int numFriends = 0;
        boolean flag = false;
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter ID of person: ");

        do {
            try {
                idNum = scanner.nextInt();
                flag=false;
            }
            catch (InputMismatchException e) {
                System.out.printf("\nINVALID\n");
                scanner.nextLine();
                flag=true;
            }
        } while(flag);

        for (int i = 0; i < this.graph[idNum].length; i++)
        {
            if (this.graph[idNum][i] == true)
            {
                System.out.printf("\nFriend ID: " + i);
                numFriends = numFriends + 1;
            }
        }

        System.out.printf("\nPerson %d has %d friends", idNum, numFriends);
    }

    public ArrayList<Integer> findPath() {
        Scanner input = new Scanner(System.in);
        int source=0, dest=0;
        boolean[] visited = new boolean[this.noAccs];
        ArrayList<Integer> queue = new ArrayList<Integer>();
        boolean flag = false;

        //input for source
        do {
            try {
                System.out.printf("Enter ID of first person: ");
                source = input.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.printf("\nINVALID\n");
                flag = true;
            }
            if (source<0||source>this.noAccs) {
                System.out.printf("\nINVALID\n");
                flag = true;
            }
        } while(flag);

        //input for dest
        do {
            try {
                System.out.printf("Enter ID of second person: ");
                dest = input.nextInt();
                flag = false;
            } catch (InputMismatchException e) {
                System.out.printf("\nINVALID\n");
                flag = true;
            }
            if (dest<0||dest>this.noAccs) {
                System.out.printf("\nINVALID\n");
                flag = true;
            }
        } while(flag);

        visited[source] = true;
        visited[dest] = false;
        flag = this.dfs(source, dest, visited, queue);
        queue.add(source);
        queue.add(0,dest);

        if (flag) {
            System.out.printf("\nThere is a path between %d and %d", source, dest);
            return queue;
        }
        else {
            System.out.printf("\nThere is no path between %d and %d", source, dest);
            return null;
        }
    }

    public boolean dfs(int source, int dest, boolean[] visited, ArrayList<Integer> queue) {
        if (this.graph[source][dest]==true) {
            System.out.printf("\n%d %d", source, dest);
            return true;
        }

        for(int i = 0; i<this.noAccs; i++) {
            if (this.graph[source][i]==true&&visited[i]==false) {
                visited[i]=true;
                if (this.dfs(i,dest,visited,queue)==true) {
                    System.out.printf("\n%d %d", source, i);
                    queue.add(i);
                    return true;
                }
            }
        }

        return false;
    }
}