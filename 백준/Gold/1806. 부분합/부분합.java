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
	static int N, S, num[];

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		num = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			num[i] = Integer.parseInt(st.nextToken());

		int length = Integer.MAX_VALUE;
		int start = 1;
		int end = 1;
		int partialSum = 0;

		/**
		 * 투포인터로 부분합 구하기
		 */
		while (true) {

			if (partialSum >= S && length > end - start)
				length = end - start;

			//System.out.printf("시작:%d 끝:%d 부분합:%d 길이:%d\n", start, end, partialSum, length);

			if (partialSum < S) {
				if (end > N)
					break;
				partialSum += num[end++];
			} else {
				if (start == N)
					break;
				partialSum -= num[start++];
			}
		}

		if (length == Integer.MAX_VALUE)
			System.out.println(0);
		else
			System.out.println(length);

	}
}