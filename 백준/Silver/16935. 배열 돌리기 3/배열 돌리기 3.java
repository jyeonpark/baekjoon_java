import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[][] arr;
	static int n, m, r;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++) {
			int num = Integer.parseInt(st.nextToken());
			switch (num) {
			case 1: // 상하반전 -> 한 열(col)씩 stack에 넣고, pop해서 재정렬한다.
				Stack<Integer> st1 = new Stack<Integer>();
				n = arr.length;
				m = arr[0].length;

				int new_arr1[][] = new int[n][m];
				for (int x = 0; x < m; x++) {
					st1.clear();
					for (int y = 0; y < n; y++) {
						st1.push(arr[y][x]);
					}
					for (int y = 0; y < n; y++) {
						new_arr1[y][x] = st1.pop();
					}
				}
				arr = new_arr1;
				new_arr1 = null;

				break;
			case 2: // 좌우반전 -> 한 행(row)씩 stack에 넣고, pop해서 재정렬한다.
				Stack<Integer> st2 = new Stack<Integer>();
				n = arr.length;
				m = arr[0].length;
				int new_arr2[][] = new int[n][m];
				for (int x = 0; x < n; x++) {
					st2.clear();
					for (int y = 0; y < m; y++) {
						st2.push(arr[x][y]);
					}
					for (int y = 0; y < m; y++) {
						new_arr2[x][y] = st2.pop();
					}
				}
				arr = new_arr2;
				new_arr2 = null;


				break;
			case 3: // 오른쪽으로 90도 회전 -> 한 열(col) 씩 stack에 넣고, pop해서 새로운 행에 재정렬한다.
				Stack<Integer> st3 = new Stack<Integer>();
				n = arr.length;
				m = arr[0].length;
				int new_arr3[][] = new int[m][n];
				for (int x = 0; x < m; x++) {
					st3.clear();
					for (int y = 0; y < n; y++) {
						st3.push(arr[y][x]);
					}
					for (int y = 0; y < n; y++) {
						new_arr3[x][y] = st3.pop();
					}
				}
				arr = new_arr3;
				new_arr3 = null;

				break;
				
			case 4:// 왼쪽으로 90도 회전 -> 한 헹(row) 씩 queue에 넣고, poll해서 새로운 열에 재정렬한다.
				Queue<Integer> q4 = new LinkedList<Integer>();
				n = arr.length;
				m = arr[0].length;
				int new_arr4[][] = new int[m][n];
				for (int x = m - 1; x >= 0; x--) {
					q4.clear();
					for (int y = 0; y < n; y++) {
						q4.add(arr[y][x]);
					}
					for (int y = 0; y < n; y++) {
						new_arr4[m - 1 - x][y] = q4.poll();
					}
				}
				arr = new_arr4;
				new_arr4 = null;


				break;
			case 5:
				n = arr.length;
				m = arr[0].length;
				int new_arr5[][] = new int[n][m];
				for (int x = 0; x < n / 2; x++) { // 1->2
					for (int y = 0; y < m / 2; y++) {
						new_arr5[x][y + m / 2] = arr[x][y];
					}
				}
				for (int x = 0; x < n / 2; x++) { // 2->3
					for (int y = m / 2; y < m; y++) {
						new_arr5[x + n / 2][y] = arr[x][y];
					}
				}
				for (int x = n / 2; x < n; x++) { // 3->4
					for (int y = m / 2; y < m; y++) {
						new_arr5[x][y - m / 2] = arr[x][y];
					}
				}

				for (int x = n / 2; x < n; x++) { // 4->1
					for (int y = 0; y < m / 2; y++) {
						new_arr5[x - n / 2][y] = arr[x][y];
					}
				}
				arr = new_arr5;
				new_arr5 = null; 


				break;
			case 6:
				n = arr.length;
				m = arr[0].length;
				int new_arr6[][] = new int[n][m];
				for (int x = 0; x < n / 2; x++) { // 1->4
					for (int y = 0; y < m / 2; y++) {
						new_arr6[x + n / 2][y] = arr[x][y];
					}
				}
				for (int x = 0; x < n / 2; x++) { // 2->1
					for (int y = m / 2; y < m; y++) {
						new_arr6[x][y - m / 2] = arr[x][y];
					}
				}
				for (int x = n / 2; x < n; x++) { // 3->2
					for (int y = m / 2; y < m; y++) {
						new_arr6[x - n / 2][y] = arr[x][y];
					}
				}

				for (int x = n / 2; x < n; x++) { // 4->3
					for (int y = 0; y < m / 2; y++) {
						new_arr6[x][y + m / 2] = arr[x][y];
					}
				}
				arr = new_arr6;
				new_arr6 = null;


				break;
			default:
				break;
			}
		}
		print();
	}

	static void print() {
		for (int[] ar: arr) {
			for (int a :ar) {
				sb.append(a).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
