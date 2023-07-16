import java.util.*;
import java.io.*;

public class Driver {
    public static void main(String[] args) {
        File f = new File("data/Caltech36.txt");
        Network netwk = new Network(f);
        
        ArrayList<Integer> queue = netwk.findPath(6,100);

        for(int i : queue) {
            System.out.printf("\n%d", i);
        }
    }
}