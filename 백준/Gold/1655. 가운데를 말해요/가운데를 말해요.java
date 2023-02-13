import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		int n = Integer.parseInt(br.readLine());
		int num;
		for(int i=0; i<n; i++) {
			num = Integer.parseInt(br.readLine());
			
			if ( maxHeap.size() == minHeap.size() ) {
				if (minHeap.size() > 0 && num > minHeap.peek()) {
					maxHeap.add(minHeap.poll());
					minHeap.add(num);
				}else {
					maxHeap.add(num);
				}
			} else {
				if (maxHeap.size() > 0 && num < maxHeap.peek()) {
					minHeap.add(maxHeap.poll());
					maxHeap.add(num);
				} else {
					minHeap.add(num);
				}
			}
			
			sb.append(maxHeap.peek()).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}