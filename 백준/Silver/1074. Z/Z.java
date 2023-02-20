import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, R, C, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		cut(R, C, (int) Math.pow(2, N));
	}

	private static void cut(int r, int c, int size) {
		if (size == 1) {
			System.out.println(cnt);
			return;
		}

		int half = size / 2;
		if (r < half && c < half) { // 1사분면
			cnt += half * half * 0;
			cut(r, c, half);
		} else if (r < half && c < half + half) { // 2사분면
			cnt += half * half * 1;
			cut(r, c - half, half);
		} else if (r < half + half && c < half) { // 3사분면
			cnt += half * half * 2;
			cut(r - half, c, half);
		} else if (r < half + half && c < half + half) { // 4사분면
			cnt += half * half * 3;
			cut(r - half, c - half, half);
		}
	}

}