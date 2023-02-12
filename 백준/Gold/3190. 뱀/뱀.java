import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int k;
	static Queue<int[]> snake;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][] board;
	static Map<Integer, Character> rotation;
	static Iterator<int[]> iterator;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		StringTokenizer st;
		board = new int[n + 1][n + 1];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			board[row][col] = 1; // 사과 위치
		}
		int L = Integer.parseInt(br.readLine());
		rotation = new HashMap<Integer, Character>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			char dir = (st.nextToken()).charAt(0);
			rotation.put(sec, dir);
		}

		int direction = 0;
		int time = 0;
		int head_x = 1;
		int head_y = 1;
		snake = new LinkedList<int[]>(); // 뱀의 몸이 위치한 모든 좌표
		snake.add(new int[] { 1, 1 });
		while (true) {

			time++;

			// 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			head_x += dx[direction];
			head_y += dy[direction];

			// 종료조건을 만족하지 않는다면
			if (!finish(head_x, head_y)) {

				// 만약 이동한 칸에 사과가 있다면
				if (board[head_x][head_y] == 1) {
					board[head_x][head_y] = 0; // 사과를 없앤다.
					// 꼬리는 움직이지 않는다.
				}

				else { // 이동한 칸에 사과가 없다면
					snake.poll(); // 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
				}

			} else { // 종료
				System.out.println(time);
				break;
			}

			snake.add(new int[] { head_x, head_y });

			//System.out.printf("%d초 방향:%d\n", time, direction);
			//System.out.printf("뱀 머리 : (%d,%d)\n", head_x, head_y);

			if (rotation.containsKey(time)) { // 회전할 시간이라면
				int dir = rotation.get(time);
				if (dir == 'L') {
					direction = direction== 0 ? 3 : (direction - 1) % 4;
				} else if (dir == 'D') {
					direction = (direction + 1) % 4;
				}
			}

		}
	}

	// 종료조건을 만족하는지 확인
	public static boolean finish(int nx, int ny) {
		if (nx < 1 || nx > n || ny < 1 || ny > n) { // 벽에 부딪힌다면
			return true;
		}
		
		iterator = snake.iterator();
		while (iterator.hasNext()) {
			int[] tmp = iterator.next();
			if (tmp[0] == nx && tmp[1] == ny) { // 자기자신의 몸과 부딪힌다면
				return true;
			}
		}
		return false;
	}

}