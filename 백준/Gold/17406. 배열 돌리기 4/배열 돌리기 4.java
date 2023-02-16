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
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, K, r, c, s;
	static int origin_arr[][];
	static int arr[][];
	static int[][] op_list;
	static int min_val = Integer.MAX_VALUE;
	static int[] order;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		origin_arr = new int[N][M];
		arr = new int[N][M];
		op_list = new int[K][3]; // K개의 회전 연산의 r,c,s를 저장하는 배열
		order = new int[K]; // K개의 회전 연산의 순서를 저장하는 배열
		for (int i = 0; i < K; i++) {
			order[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				origin_arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 회전 연산
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			op_list[i][0] = Integer.parseInt(st.nextToken());
			op_list[i][1] = Integer.parseInt(st.nextToken());
			op_list[i][2] = Integer.parseInt(st.nextToken());
		}


		do {
			
			for(int i=0; i<N; i++) {
				System.arraycopy(origin_arr[i], 0,  arr[i], 0, M);
			}
	
			// 연산의 순열에 따라 회전하기
			for (int i = 0; i < K; i++) {
				int now = order[i];
				int r = op_list[now][0];
				int c = op_list[now][1];
				int s = op_list[now][2];
				rotate(r - s - 1, c - s - 1, r + s - 1, c + s - 1);
			}

			// 최솟값 갱신하기
			for (int[] ar : arr) {
				int total = 0;
				for (int a : ar) {
					total += a;
				}
				min_val = Math.min(min_val, total);
			}
//			
//			for (int[] ar : arr) {
//				for (int a : ar) {
//					System.out.print(a + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();

		} while (np(order)); // next_permutation으로 다음 순열 갱신하기

		System.out.println(min_val);
	}

	static boolean np(int[] order) {

		// step1. 뒤쪽부터 꼭대기를 찾는다. (꼭대기 바로 앞이 교환할 자리)
		int i = K - 1;
		while (i > 0 && order[i - 1] >= order[i])
			--i;
		if (i == 0)
			return false;

		// step2. 꼭대기 바로 앞(i-1) 자리에 교환할 값을 뒤쪽부터 찾는다.
		int j = K - 1;
		while (order[i - 1] >= order[j])
			--j;

		// step3. 꼭대기 바로 앞(i-1) 자리와 그 자리값보다 한 단계 큰 자리(j) 수와 변경
		swap(order, i - 1, j);

		// step4. 꼭대기부터 맨 뒤까지 오름차순으로 정렬
		int k = K - 1;
		while (i < k)
			swap(order, i++, k--);

		return true;
	}

	static void swap(int[] input, int i, int j) {
		int temp = order[i];
		order[i] = order[j];
		order[j] = temp;
	}

	static void rotate(int start_x, int start_y, int end_x, int end_y) {

		int cnt = (end_x - start_x + 1) / 2;

		for (int c = 0; c < cnt; c++) { // 회전하는 테두리 직사각형 갯수
			Queue<Integer> q = new LinkedList<Integer>();

			/**
			 * stack에 넣기
			 */
			// 세로1
			for (int i = start_x + c; i <= end_x - c; i++) {
				q.add(arr[i][start_y + c]);
			}

			// 가로1
			for (int i = start_y + c + 1; i <= end_y - c - 1; i++) {
				q.add(arr[end_x - c][i]);
			}

			// 세로2
			for (int i = end_x - c; i >= start_x + c; i--) {
				q.add(arr[i][end_y - c]);
			}

			// 가로2
			for (int i = end_y - c - 1; i >= start_y + c + 1; i--) {
				q.add(arr[start_x + c][i]);
			}

			/**
			 * 회전한 것 다시 배열에 넣기
			 */
			q.add(q.poll());

			// 세로1
			for (int i = start_x + c; i <= end_x - c; i++) {
				arr[i][start_y + c] = q.poll();
			}

			// 가로1
			for (int i = start_y + c + 1; i <= end_y - c - 1; i++) {
				arr[end_x - c][i] = q.poll();
			}

			// 세로2
			for (int i = end_x - c; i >= start_x + c; i--) {
				arr[i][end_y - c] = q.poll();
			}

			// 가로2
			for (int i = end_y - c - 1; i >= start_y + c + 1; i--) {
				arr[start_x + c][i] = q.poll();
			}
		}
	}

}
