import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[][] arr = new int[n][n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int max_cnt = 0;
			for(int i=0; i<n-m+1; i++) {
				for(int j=0; j<n-m+1; j++) {
					int cnt = 0;
					for(int x=0; x<m; x++) {
						for(int y=0; y<m; y++) {
							cnt += arr[i+x][j+y];
						}
					}
					max_cnt = Math.max(max_cnt, cnt);
				}
			}
			System.out.printf("#%d %d\n",t,max_cnt);
		}
	}

}
