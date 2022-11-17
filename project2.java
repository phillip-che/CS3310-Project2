import java.util.*;

public class project2 {

    public static void main(String[] args) {
        int[][] graph = {
            {0,50,45,10,0,0},
            {0,0,10,15,0,0},
            {0,0,0,0,30,0},
            {20,0,0,0,15,0},
            {0,20,35,0,0,0},
            {0,0,0,0,3,0}
        };
        // System.out.println(Arrays.deepToString(denseGraphGen(5)));
        // System.out.println(Arrays.deepToString(sparseGraphGen(5)));
        System.out.println(Arrays.toString(dijkstra(graph, 0, graph.length)));
    }
    
    public static int[] dijkstra(int[][] graph, int s, int n) {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(graph[s][i] == 0 && s != i) {
                dist[i] = Integer.MAX_VALUE;
            } else {
                dist[i] = graph[s][i];
            }
        }

        visited[s] = true;

        for(int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int u = -1;

            for(int j = 0; j < n; j++) {
                if(dist[j] != 0 && !visited[j] && dist[j] < min) {
                    min = dist[j];
                    u = j;
                }
            }
            
            if(u == -1) {
                break;
            }
                
            visited[u] = true;

            for(int j = 0; j < n; j++) {
                if(!visited[j] && graph[u][j] != 0) {
                    dist[j] = Math.min(dist[j], dist[u] + graph[u][j]);
                }
            }
        }
        
        return dist;
    }

    public static void floydWarshall(int[][] graph) {
        
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