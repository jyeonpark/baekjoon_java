import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n, m, arr[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int[] dy = {-1, 0, 1};
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int y = 0; y < m; y++) q.add(new Node(0, y, arr[0][y], -1)); // 첫번째 행
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == n - 1) return cur.fuel;
            for (int d = 0; d < 3; d++) {
                if (cur.dir != d) {
                    int nx = cur.x + 1;
                    int ny = cur.y + dy[d];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                        q.add(new Node(nx, ny, cur.fuel+arr[nx][ny], d));
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int fuel;
        int dir;

        public Node(int x, int y, int fuel, int dir) {
            this.x = x;
            this.y = y;
            this.fuel = fuel;
            this.dir = dir;
        }

        @Override
        public int compareTo(Node o) {
            return this.fuel - o.fuel;
        }
    }
}