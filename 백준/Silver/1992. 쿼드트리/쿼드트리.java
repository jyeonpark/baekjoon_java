import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int n, arr[][];
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		zip(0,0,n);
		System.out.println(sb.toString());
	}

	static void zip(int r, int c, int size) {
		if (size==0) {
			sb.append(arr[r][c]);
			return;
		}
		
		if (getSum(r, c, size) == size*size) { // 모두 1인 경우
			sb.append(1);
			return;
		}
		if (getSum(r, c, size) == 0) { // 모두 0인 경우
			sb.append(0);
			return;
		}
		
		// 4조각 내기
		int half = size/2;
		sb.append("(");
		zip(r,c,half);
		zip(r,c+half, half);
		zip(r+half, c, half);
		zip(r+half, c+half, half);
		sb.append(")");
	}
	
	static int getSum(int r, int c, int size) {
		int total=0;
		for(int i=r; i<r+size; i++) {
			for(int j=c; j<c+size; j++) {
				total+=arr[i][j];
			}
		}
		return total;
	}

}