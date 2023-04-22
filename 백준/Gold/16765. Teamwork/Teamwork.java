import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] cow = new int[N]; // i가 속한 그룹의 most-skilled cow
		int[] dp = new int[N + 1];

		for (int i = 0; i < N; i++) {
			cow[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < N; i++) {
			int max = 0; // 그룹 최댓값
			for (int j = 0; j < K; j++) { // 현재 소를 k개가 속한 그룹에 묶을 때 
				if (i >= j) { 
					if (cow[i - j] > max)
						max = cow[i - j]; 
					dp[i+1] = Math.max(dp[i+1], dp[i - j] + max * (j + 1));
				}
			}
			//System.out.printf("dp[%d] : %d\n", i, dp[i+1]);
		}
		
		System.out.println(dp[N]);
	}

}