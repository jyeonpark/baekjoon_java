import java.io.*;
import java.util.*;

public class Main {
    final static int MAX = 50000001;
    static int n, m, a, b, c, dist[];
    static ArrayList<Node>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n + 1];
        dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, c));
            arr[b].add(new Node(a, c));
        }
        go();
        bw.write(Integer.toString(dist[n]));
        bw.flush();
        bw.close();
    }

    static void go() {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1, 0));
        Arrays.fill(dist, MAX);
        dist[1] = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (visited[now.node])  continue;
            visited[now.node] = true;
            for (Node next : arr[now.node]) {
                if (dist[next.node] > dist[now.node] + next.cost) {
                    dist[next.node] = dist[now.node] + next.cost;
                    q.offer(new Node(next.node, dist[next.node]));
                }
            }

        }
    }

    static class Node implements Comparable<Node> {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}