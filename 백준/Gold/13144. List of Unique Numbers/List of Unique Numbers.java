import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    static int n, l=1, r=0, list[], visited[];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new int[n + 1];
        visited = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) list[i] = Integer.parseInt(st.nextToken());

        long ans = 0L;
        while(l<=n) {
            while (r + 1 <= n && visited[list[r + 1]] == 0) { // 오른쪽 포인터 움직이기
                visited[list[++r]]++;
            }
            ans += (r - l + 1);
            visited[list[l++]]--;
        }
        System.out.println(ans);
    }
}