import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int[] dp = new int[d + 1];
		List<int[]> shortcuts = new ArrayList<int[]>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			shortcuts.add(new int[] { Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()) });
		}
		for(int i=1; i<=d; i++) {
			dp[i] = dp[i-1]+1;
			for(int[] shortcut : shortcuts) {
				if (shortcut[1] == i) { // i가 지름길의 도착지점일때 비교한다
					dp[i] = Math.min(dp[i], dp[shortcut[0]]+shortcut[2]);
				}
			}
		}
		System.out.println(dp[d]);

	}

}
