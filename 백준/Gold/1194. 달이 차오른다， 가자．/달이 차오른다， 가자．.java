import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public class Main {

	static int n, m, nowx, nowy;
	static char[][] map;
	static String line;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			line = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '0') { // 현재 위치
					nowx = i;
					nowy = j;
				}
			}
		}
		System.out.println(bfs());
	}

	static int bfs() {
		PriorityQueue<Move> q = new PriorityQueue<>();
		boolean visited[][][] = new boolean[n][m][1<<'g'];
		q.add(new Move(nowx, nowy, 0, 0));
		visited[nowx][nowy][0] = true;

		while (!q.isEmpty()) {
			Move info = q.poll();
//			System.out.printf("(%d,%d) 방문! 지금까지 %d횟수로 이동\n", info.x, info.y, info.cnt);
			for(int i=0; i<4; i++) {
				int nx = info.x + dx[i];
				int ny = info.y + dy[i];
				if (isRange(nx, ny) && !visited[nx][ny][info.keys]) {
					char c = map[nx][ny];
					int keys = info.keys;
//					System.out.printf("(%d,%d)좌표 : %c\n", nx, ny, c);

					if (c == '#')	continue; // 벽이라면 이동X
					if (c>= 'a' && c<='f')	{
						keys |= (1<<c); // 열쇠라면 집는다.
//						System.out.println("열쇠!!");
					}
					
					if (c>= 'A' && c<='F' 
							&& (keys & (1<<(Character.toLowerCase(c)))) == 0) { // 문일때 열쇠가 없다면 이동X
//						System.out.printf("열쇠없어\n", nx,ny);
						continue;
					}
					if (c == '1')	return info.cnt+1;
					
					q.add(new Move(nx, ny, info.cnt+1, keys));
					visited[nx][ny][keys] = true;
				}
			}
		}

	return-1;

	}

	static boolean isRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

	static class Move implements Comparable<Move> {
		int x;
		int y;
		int cnt;
		int keys;

		
		public Move(int x, int y, int cnt, int keys) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.keys = keys;
		}

		@Override
		public int compareTo(Move o) {
			return this.cnt - o.cnt;
		}
	}
}
