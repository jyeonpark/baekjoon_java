import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static Queue<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int[] order = { 1, 2, 3, 4, 5 };

		for (int t = 1; t <= 10; t++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			int idx = 0;
			for (int i = 0; i < 8; i++)
				q.add(Integer.parseInt(st.nextToken()));

			while (true) {
				int n = q.poll() - order[idx++ % 5];
				if (n <= 0) {
					n = 0;
					q.add(n);
					break;
				}
				q.add(n);
			}

			sb.append("#").append(t).append(" ");
			for (Integer i : q)
				sb.append(i + " ");
			sb.append("\n");
			
			q.clear();
		}

		System.out.println(sb.toString());
	}
}
