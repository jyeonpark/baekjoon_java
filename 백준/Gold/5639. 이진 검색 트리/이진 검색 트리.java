import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static StringTokenizer st;
	static ArrayList<Integer> nodes = new ArrayList<>();
	static int size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		String s;
		while ((s = br.readLine()) != null) {
			nodes.add(Integer.parseInt(s));
			size++;
		}
		
		postOrder(0, size-1);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void postOrder(int startNodeIdx, int endNodeIdx) {
		if (startNodeIdx > endNodeIdx) return;
		
		int cur = nodes.get(startNodeIdx);
		int startRightIdx = endNodeIdx+1;
		
		for(int i=startNodeIdx+1; i<=endNodeIdx; i++) {
			if (nodes.get(i) > cur) {
				startRightIdx = i;
				break;
			}
		}
		
		postOrder(startNodeIdx+1, startRightIdx-1);
		postOrder(startRightIdx, endNodeIdx);
		
		sb.append(cur).append("\n");
	}

}