import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int[][] arr;
	static int white = 0;
	static int blue = 0;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cut(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}

	static void cut(int r, int c, int size) {
		int sum = 0;
		for (int i = r, iEnd = r + size; i < iEnd; i++) {
			for (int j = c, cEnd = c + size; j < cEnd; j++) {
				sum += arr[i][j];
			}
		}
		if (sum == size * size) { // 모두 파랑색
			blue++;
		} else if (sum == 0) { // 모두 흰색
			white++;
		} else { // 4분할하기
			int half = size / 2;
			cut(r, c, half);
			cut(r, c + half, half);
			cut(r + half, c, half);
			cut(r + half, c + half, half);
		}
	}
}
