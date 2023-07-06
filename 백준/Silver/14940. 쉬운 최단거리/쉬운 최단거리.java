import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, startx, starty, map[][], dist[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    startx = i;
                    starty = j;
                }
                else if (map[i][j] == 0) {
                    dist[i][j] = 0;
                }
            }
        }
        bfs();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startx,starty));
        dist[startx][starty] = 0;
        while(!q.isEmpty()){
            Point now = q.poll();
            for(int d=0; d<4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                // 범위 안이면서 아직 방문하지 않았고, 갈 수 있는 땅이라면 이동한다
                if (isRanged(nx,ny) && dist[nx][ny] == -1 && map[nx][ny] == 1){
                    dist[nx][ny] = dist[now.x][now.y] + 1;
                    q.add(new Point(nx,ny));
                }
            }
        }
    }

    static Boolean isRanged(int x, int y){
        return x>=0 && x<n && y>=0 && y<m;
    }
}