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
	static int n, m, k, op;
	static long b, c,arr[],tree[];

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		double height = Math.ceil(Math.log(n) / Math.log(2)) + 1;
		tree = new long[Math.toIntExact(Math.round(Math.pow(2, height)))];

		init(1, 1, n);

		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			op = Integer.parseInt(st.nextToken());
			b = Long.parseLong(st.nextToken());
			c = Long.parseLong(st.nextToken());
			if (op == 1) { // b를 c로 바꾼다.
				long diff = c - arr[Math.toIntExact(b)];
				arr[Math.toIntExact(b)] = c;
				change(1, 1, n, Math.toIntExact(b), diff);
			} else if (op == 2) { // b~c의 합을 구한다.
				sb.append(sum(1,1,n,Math.toIntExact(b),Math.toIntExact(c))).append("\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	// 세그먼트 트리 노드 값 초기화
	static long init(int node, int start, int end) {
		if (start == end) {
			return tree[node] = arr[start];
		} else {
			return tree[node] = init(node * 2, start, (start + end) / 2)
					+ init(node * 2 + 1, (start + end) / 2 + 1, end);
		}
	}

	// 구간합 구하기
	static long sum(int node, int start, int end, int left, int right) {
		if (start > right || end < left) { // 구간합 범위를 벗어난다면 0 리턴
			return 0;
		} else if (left <= start && end <= right) {
			// System.out.printf("tree[%d] : %d\n", node, tree[node]);
			return tree[node]; // 노드가 가지는 값의 구간이 구간합 구간에 속하는 경우 노드 값 리턴
		} else { // 구간합 구간에 걸친다면
			return sum(node * 2, start, (start + end) / 2, left, right)
					+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		}
	}

	// 수 변경하기
	static void change(int node, int start, int end, int idx, long diff) {
		if (idx < start || end < idx) { // 범위 끝까지 탐색한 경우
			return;
		} else {
			tree[node] += diff; // 범위 안에 있는 숫자도 모두 변경한다.
			if (start != end) { // 리프노드가 아닌 경우
				change(node * 2, start, (start + end) / 2, idx, diff);
				change(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
			}
		}
	}

}