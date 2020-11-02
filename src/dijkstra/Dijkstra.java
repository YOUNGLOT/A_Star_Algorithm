package dijkstra;

import java.util.Arrays;

public class Dijkstra {
    private final int INF = Integer.MAX_VALUE;
    private int[] distance;
    private final boolean[] check;

    private final int[][] input;

    private final int start;
    private final int end;

    public Dijkstra(int[][] input, int start, int end) {
        this.input = input;
        this.distance = new int[input.length];
        this.check = new boolean[input.length];

        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        int[][] input =
                {{0, 2, INF, INF, 5, INF, INF},
                        {2, 0, 3, 8, 1, INF, INF},
                        {INF, 3, 0, 1, INF, INF, 4},
                        {INF, 8, 1, 0, 5, 1, 5},
                        {5, 1, INF, 5, 0, INF, INF},
                        {INF, INF, INF, 1, INF, 0, 1},
                        {INF, INF, 4, 5, INF, 1, 0}};


        Dijkstra dijkstra = new Dijkstra(input, 0, 6);
        dijkstra.solve();
    }

    private void solve() {
        this.distance = input[start];
        Arrays.fill(check, true);
        String s = "";


        if (check[0] == true)
            check[0] = false;


        //  최솟값 가져오고
        int min = INF;
        int index = -1;
        for (int k = 1; k <= check.length; k++) {
            if (check[k] == true && min > distance[k]) {
                min = distance[k];
                index = k;
            }
        }

        check[index] = false;
        s += index + " ";

        for (int i = 0; i < check.length; i++) {
            if (input[index][i] != 0 && distance[i] > distance[index] + input[index][i]) {
                distance[i] = distance[index] + input[index][i];
            }
        }

    }

}


