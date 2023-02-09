import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int S[];
	static int B[];

	static boolean isSelected[];
	static long min = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 재료 N개
		S = new int[N];
		B = new int[N];
		isSelected = new boolean[N];

		for (int j = 0; j < N; j++) {
			st = new StringTokenizer(br.readLine());
			S[j] = Integer.parseInt(st.nextToken());
			B[j] = Integer.parseInt(st.nextToken());
		}

		subSet(0);

		System.out.println(min);
	
	}

	private static void subSet(int cnt) {

		if (cnt == N) {
			int sSum = 1;
			int bSum = 0;
			int using = 0; // 공집합 체크용
			for (int i = 0; i < N; i++) {
				if (!(isSelected[i]))
					continue;
				using++;
				sSum *= S[i];
				bSum += B[i];
			}

			if (using < 1)
				return;

			int dif = Math.abs(sSum - bSum);
			min = Math.min(min, dif);

			return;
		}

		isSelected[cnt] = true;
		subSet(cnt + 1);

		isSelected[cnt] = false;
		subSet(cnt + 1);

	}
}