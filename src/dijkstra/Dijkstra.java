package dijkstra;

public class Dijkstra {
    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        int INF = Integer.MAX_VALUE;

        int[][] input = {{0, 3, 6, 7}, {3, 0, 1, INF},{6, 1, 0, 1}, {7, INF, 1, 0}};

        dijkstra.solve(input);
    }

    private void solve(int[][] input){

    }
}
