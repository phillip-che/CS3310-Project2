import java.util.*;

public class project2 {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(denseGraphGen(3)));
        System.out.println(Arrays.deepToString(sparseGraphGen(3)));
    }
    
    public static void dijkstra(int[][] graph, int[] weights, int start) {

    }

    public static void floydWarshall(int[][] graph, int[] weights) {
        
    }

    public static int[][] denseGraphGen(int n) {
        int[][] res = new int[n][n];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i != j) {
                    res[i][j] = (int) (Math.random() * 20)+1;
                }
            }
        }

        return res;
    }

    public static int[][] sparseGraphGen(int n) {
        int[][] res = new int[n][n];

        for(int i = 0; i < n; i++) {
            res[i][(i+1)%n] = (int) (Math.random() * 20)+1;
        }
        
        return res;
    }
}