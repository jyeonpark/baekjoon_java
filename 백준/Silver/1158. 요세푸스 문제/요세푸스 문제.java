import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= n; i++) {
			q.add(i);
		}

		sb.append("<");
		while (!q.isEmpty()) {
			if (q.size() == 1) {
				sb.append(q.remove()).append(">");
				break;
			} else {
				for (int i = 0; i < k - 1; i++)
					q.add(q.poll());
				sb.append(q.poll()).append(", ");
			}
		}
		System.out.println(sb.toString());
	}

}
