import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int n, map[][], dp[][];
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				ans = Math.max(ans, dfs(i,j));
			}
		}
		
		System.out.println(ans);
		
	}
	
	static int dfs(int x, int y) {
		if (dp[x][y] != 0)	return dp[x][y]; // 이미 방문한 좌표라면 
		dp[x][y] = 1; 
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			// 다음칸에 더 많은 대나무가 있다면
			if (nx>=0 &&nx<n && ny>=0 && ny<n && map[x][y] < map[nx][ny]) {
				dp[x][y] = Math.max(dp[x][y], dfs(nx,ny)+1);
			}
		}
		
		return dp[x][y];
	}
	
	
}