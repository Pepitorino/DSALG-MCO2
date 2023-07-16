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

        this.noAccs=noFriendships;
        this.noFriendships=noAccs;
        
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
        int validChoice = 0;
        Scanner scanner = new Scanner(System.in);

        while (validChoice == 0)
        {
            System.out.printf("Enter the desired ID Number: ");
            idNum = scanner.nextInt();

            if (this.graph[idNum] == null)
            {
                System.out.printf("ID Number does not exist. Please try again.");
            }

            else
            {
                validChoice = 1;
            }
        }

        for (int i = 0; i < this.graph[idNum].length; i++)
        {
            if (this.graph[idNum][i] == true)
            {
                System.out.printf("\nFriend ID: " + i);
                numFriends = numFriends + 1;
            }
        }

        System.out.printf("\n\nTotal Number of Friends: " + numFriends);

        scanner.close();
    }

    public ArrayList<Integer> findPath(int source, int dest) {
        boolean[] visited = new boolean[this.noAccs];
        ArrayList<Integer> queue = new ArrayList<Integer>();
        visited[source] = true;

        this.dfs(source, dest, visited, queue);
        return queue;
    }

    public boolean dfs(int source, int dest, boolean[] visited, ArrayList<Integer> queue) {
        if (this.graph[source][dest]==true) {
            return true;
        }

        for(int i = 0; i<this.noAccs; i++) {
            if (this.graph[source][i]==true&&visited[i]==false) {
                visited[i]=true;
                if (dfs(i,dest,visited,queue)==true) {
                    queue.add(i);
                    return true;
                }
            }
        }

        return false;
    }
}