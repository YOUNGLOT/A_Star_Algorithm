package dijkstra;

import java.util.Arrays;

public class Dijkstra1 {
    private final int INF = Integer.MAX_VALUE;
    private final int[][] array;
    private int[] distance;
    private boolean[] found;


    public Dijkstra1(int[][] array) {
        this.array = array;
        this.distance = new int[array.length];
        this.found = new boolean[array.length];
    }

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        int[][] input =
                        {{0, 4, 2, INF, INF},
                        {INF, 0, 3, 2, 3},
                        {INF, 1, 0, 4, 5},
                        {INF, INF, INF, 0, INF},
                        {INF, INF, INF, 1, 0}};

        Dijkstra1 dijkstra1 = new Dijkstra1(input);
        dijkstra1.solve();

    }

    private void solve() {
        shortest_path(0, 5);


        System.out.println(Arrays.toString(distance));
    }

    private int choose( int n) {
        int i;
        int min = INF;
        int minpos = -1;
        for (i = 0; i < n; i++){
            if( distance[i] < min && found[i] == false ){
                min = distance[i];

                minpos = i;
            }
        }
        return minpos;
    }

    void shortest_path(int start, int n){
        int i, u, w;

        for( i = 0; i < n; i++){
            distance[i] = array[start][i];

            found[i] = false;
        }
        found[start] = true;
        //distance[start] = true;

        for (int j = 0; j < n-1; j++) {
            u = choose( n);
            found[u] = true;

            for (int k = 0; k < n; k++) {
                if(found[k] == false){
                    if(found[k] == false){
                        if(distance[u] + array[u][k] < distance[k]){
                            distance[k] = distance[u] + array[u][k];
                        }
                    }
                }
            }

        }
    }

}
