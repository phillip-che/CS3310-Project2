import java.util.*;

public class project2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Number of Tests: ");
        int tests = input.nextInt();

        System.out.print("Enter Number of Vertices: ");
        int n = input.nextInt();

        // System.out.print("Do you want to return shortest paths of all vertices? (Y or N): ");
        // char y = input.nextLine().charAt(0);

        // if(y == 'Y' || y == 'y') {

        // }

        // sanity check
        int[][] graph = {
            {0,50,45,10,0,0},
            {0,0,10,15,0,0},
            {0,0,0,0,30,0},
            {20,0,0,0,15,0},
            {0,20,35,0,0,0},
            {0,0,0,0,3,0}
        };

        // for(int i = 0; i < graph.length; i++) {
        //     System.out.println(Arrays.toString(dijkstra(graph, i, graph.length)));
        // }

        // System.out.println(Arrays.deepToString(floydWarshall(graph, graph.length)));

        long[] ddTimes = new long[tests];
        long[] dsTimes = new long[tests];
        long[] fdTimes = new long[tests];
        long[] fsTimes = new long[tests];

        for(int i = 0; i < tests; i++) {
            int[][] dense = denseGraphGen(n);                                    
            int[][] sparse = sparseGraphGen(n);

            long start = System.nanoTime();
            for(int j = 0; j < n; j++) {
                dijkstra(dense, j, n);
            }
            long end = System.nanoTime();
            long time = end - start;
            ddTimes[i] = time;

            start = System.nanoTime();
            for(int j = 0; j < n; j++) {
                dijkstra(sparse, j, n); 
            }
            end = System.nanoTime();
            time = end - start;
            dsTimes[i] = time;

            start = System.nanoTime();
            floydWarshall(dense, n);
            end = System.nanoTime();
            time = end - start;
            fdTimes[i] = time;

            start = System.nanoTime();
            floydWarshall(sparse, n);
            end = System.nanoTime();
            time = end - start;
            fsTimes[i] = time;
        }

        System.out.println("----------------------------------------------------------");
        System.out.println("Execution Times of Dijkstra's Algorithm with Dense Graphs:\n" + Arrays.toString(ddTimes) + "\n");
        System.out.println("Execution Times of Dijkstra's Algorithm with Sparse Graphs:\n" + Arrays.toString(dsTimes) + "\n");
        System.out.println("Execution Times of Floyd Warshall's Algorithm with Dense Graphs:\n" + Arrays.toString(fdTimes) + "\n");
        System.out.println("Execution Times of Floyd Warshall's Algorithm with Sparse Graphs:\n" + Arrays.toString(fsTimes));

        // for(int i = 0; i < n; i++) {
        //     int[][] dense = denseGraphGen(n);
        //     int[][] sparse = sparseGraphGen(n);

        //     System.out.printf("Shortest Paths of Vertex %d to All Other Vertices\n", i+1);
        //     System.out.println(Arrays.toString(dijkstra(dense, i, n)));
        //     System.out.println(Arrays.toString(dijkstra(sparse, i, n)));
        //     System.out.println(Arrays.deepToString(floydWarshall(dense, n)));
        //     System.out.println(Arrays.deepToString(floydWarshall(sparse, n)));
        // }

        // int[][] dense = denseGraphGen(n);                                    
        // int[][] sparse = sparseGraphGen(n);
        
        // for(int i = 0; i < n; i++) {
        //     System.out.println(Arrays.toString(dijkstra(dense, i, n)));
        // }

        // System.out.println(Arrays.deepToString(floydWarshall(dense, n)));

        
        // for(int i = 0; i < n; i++) {
        //     System.out.println(Arrays.toString(dijkstra(sparse, i, n)));
        // }
        
        // System.out.println(Arrays.deepToString(floydWarshall(sparse, n)));
    }
    
    public static int[] dijkstra(int[][] graph, int s, int n) {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(graph[s][i] == 0 && s != i) {
                dist[i] = 999999999;
            } else {
                dist[i] = graph[s][i];
            }
        }

        visited[s] = true;

        for(int i = 0; i < n; i++) {
            int min = 999999999;
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

    public static int[][] floydWarshall(int[][] graph, int n) {
        int[][] d = new int[n][n];

        for(int u = 0; u < n; u++) {
            for(int v =  0; v < n; v++) {
                if(u != v && graph[u][v] == 0) {
                    d[u][v] = 999999999;
                } else {
                    d[u][v] = graph[u][v];
                }
            }
        }
        
        for(int k = 0; k < n; k++) {
            for(int u = 0; u < n; u++) {
                for(int v =  0; v < n; v++) {
                    if(d[u][v] != 0 && d[u][k] != 0 && d[k][v] != 0) {
                        d[u][v] = Math.min(d[u][v], d[u][k] + d[k][v]);
                    }
                }
            }
        }
        
        return d;
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