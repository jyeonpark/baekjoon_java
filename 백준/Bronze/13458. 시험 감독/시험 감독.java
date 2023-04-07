import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, B, C, students[];
	static long cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		students = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			students[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 총감독1명, 나머지는 부감독으로 채우기
		for (int i : students) {
			long total = i;
			total -= B;
			if (total > 0) {
				cnt += (1 + (total / C));
				if (total < C) {
					cnt += 1;
				} else if (total % C > 0) {
					cnt += 1;
				}
			} else {
				cnt += 1;
			}
		}

		System.out.println(cnt);

	}

}
