import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int T, n,m, dp[][];


	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(br.readLine());
		
		dp = new int[30][30];
		for(int i=0; i<30; i++) {
			dp[i][i] = 1;
			dp[i][0] = 1;
		}
		
		for(int i=2; i<30; i++) {
			for(int j=1; j<30; j++) {
				dp[i][j]  = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			System.out.println(dp[m][n]);
		}
	}
}