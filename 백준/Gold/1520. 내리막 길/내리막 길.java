import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    static int n,m,map[][];
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static long[][] dp = new long[n][m]; // 좌표(x,y)~(n,m)까지 이동가능한 경로의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0,0));
    }

    static long dfs(int x, int y){
        if (x == m-1 && y == n-1){ // (n,m) 좌표 도달시 경우의 수 1추가
            return 1;
        }
        if (dp[x][y] != -1) return dp[x][y]; // 이미 탐색한 좌표

        dp[x][y] = 0;
        for(int d = 0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx>=0 && nx<m && ny>=0 && ny<n
                    && map[x][y] > map[nx][ny]) { // (nx,ny) 좌표로 이동 가능
                    dp[x][y] += dfs(nx,ny);
            }
        }
        return dp[x][y];
    }
}