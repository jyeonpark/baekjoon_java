import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] list;
    static boolean[] visited;
    static int max_cost = 0;
    static int node; // 가장 멀리 떨어져있는 노드

    public static class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        list = new ArrayList[n+1];
        visited = new boolean[n+1];
        for(int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v==-1)  break;
                int w = Integer.parseInt(st.nextToken());
                list[e].add(new Node(v,w));
            }
        }

        visited[1] = true;
        dfs(1,0); // 정점1에서 가장 먼 노드 찾기

        Arrays.fill(visited, false);

        visited[node] = true;
        dfs(node, 0); // 정점1에서 가장 먼 노드에서 가장 먼 노드 찾기

        System.out.println(max_cost);
    }

    public static void dfs(int x, int cost){
        if (cost>max_cost){
            max_cost = cost;
            node = x;
        }

        for(int i=0; i<list[x].size(); i++){
            Node n = list[x].get(i);
            if (!visited[n.v]){
                visited[n.v] = true;
                dfs(n.v, cost+n.w);
            }
        }
    }
}