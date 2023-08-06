import java.util.*;

class Solution {
    static int n,m,startX, startY,endX, endY;
    static char[][] map;
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        map = new char[n][m];
        for(int i=0; i<n; i++){
            map[i] = board[i].toCharArray();
            for(int j=0; j<m; j++){
                if (map[i][j] == 'R'){
                    startX = i;
                    startY = j;
                    break;
                } 
            }
        }
        return bfs();
    }
    
    static int bfs(){
        PriorityQueue<State> q = new PriorityQueue<>();
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        boolean[][] visited = new boolean[n][m];
        visited[startX][startY] = true;
        q.add(new State(startX, startY, 0));
        while(!q.isEmpty()){
            State now = q.poll();
            if (map[now.x][now.y] == 'G')  return now.cnt;
            for(int d = 0; d<4; d++){
                int nx = now.x;
                int ny = now.y;
                while(true){
                    // 벽이거나, 장애물이라면
                    if (!isRanged(nx+dx[d],ny+dy[d]) || map[nx+dx[d]][ny+dy[d]] == 'D'){
                        if (!visited[nx][ny]){ // 마지막 nx,ny를 방문하지 않았다면
                            visited[nx][ny] = true;
                            q.add(new State(nx, ny, now.cnt+1));
                        }
                        break;
                    }
                    nx += dx[d];
                    ny += dy[d];
                }
            }
        }
        return -1;
    }
    
    static boolean isRanged(int x, int y){
        return x>=0 && x<n && y>=0 && y<m;
    }
    
    static class State implements Comparable<State>{
        int x;
        int y;
        int cnt;
        
        public State(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(State s){
            return this.cnt - s.cnt;
        }
    }
}