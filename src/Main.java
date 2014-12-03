import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int dim;
        int[][] graph;
        try {
            Scanner in = new Scanner(new File("input.txt"));
            dim = in.nextInt();
            graph = new int[dim][dim];
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    graph[i][j] = in.nextInt();
                }
            }
            Graph g = new Graph(graph);
            ArrayList<ArrayList<Integer>> res = g.getMaxIndependentSets();
            System.out.println("Maximal independent sets:");
            System.out.println(res);
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}