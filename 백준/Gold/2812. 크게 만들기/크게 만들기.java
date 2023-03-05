import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	static int n, k, deleteCnt;
	static ArrayDeque<Character> deque;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		char[] input = br.readLine().toCharArray();
		deque = new ArrayDeque<Character>();

		for (int i = 0; i < input.length; i++) {
			while (k > 0 && !deque.isEmpty() && deque.peekLast() < input[i]) {
				deque.pollLast();
				k--;
			}
			deque.addLast(input[i]);
		}

		while (deque.size() > k)
			sb.append(deque.pollFirst());
		System.out.println(sb.toString());
	}
}
