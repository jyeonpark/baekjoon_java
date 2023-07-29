import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {

    static int n, num[];
    static boolean visited[];
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        num = new int[n + 1];
        visited = new boolean[n + 1];
        list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= n; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for(int i=0; i<list.size(); i++) System.out.println(list.get(i));

    }

    static void dfs(int now, int start) {
        if (!visited[num[now]]) {
            visited[num[now]] = true;
            dfs(num[now], start);
            visited[num[now]] = false;
        }

        if (num[now] == start)  list.add(start); // 사이클 발생
    }
}