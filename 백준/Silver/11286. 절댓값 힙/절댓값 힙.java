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

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		// map(key:절댓값, value:원래값)
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((o1, o2) -> {
			if (o1[0] == o2[0])
				return o1[1]-o2[1];
			return o1[0]-o2[0];
		});
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (heap.isEmpty())
					sb.append(0).append("\n");
				else	
					sb.append(heap.poll()[1]).append("\n");
			} else {
				heap.add(new int[] { Math.abs(x), x });
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
