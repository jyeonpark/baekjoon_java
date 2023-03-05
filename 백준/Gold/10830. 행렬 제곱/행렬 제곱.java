import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static final int MOD = 1000;
	static int n;
	static long b;
	static long[][] arr;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		b = Long.parseLong(st.nextToken());
		arr = new long[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = (Long.parseLong(st.nextToken())) % MOD;
			}
		}
		long[][] res = solution(b, arr);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(res[i][j]).append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static long[][] solution(long exp, long[][] x) {
		if (exp == 1L)
			return x;
		long[][] res = solution(exp / 2, x);
		res = multiply(res, res);
		if (exp % 2 == 1L) { // 홀수승이라면
			res = multiply(res, arr);
		}
		return res;
	}

	static long[][] multiply(long a[][], long b[][]) {
		long[][] res = new long[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					res[i][j] += a[i][k] * b[k][j];
					res[i][j] %= MOD;
				}
			}
		}
		return res;
	}
}