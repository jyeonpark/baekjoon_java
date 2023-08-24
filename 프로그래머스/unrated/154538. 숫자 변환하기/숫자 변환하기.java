import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = bfs(x,y,n);
        return answer;
    }
    
    static int bfs(int x, int y, int n){
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>(); // 이미 변환된 숫자라면 다시 변환X
        q.add(new int[]{x,0});
        visited.add(x);
        while(!q.isEmpty()){
            int[] info = q.poll();
            int num = info[0];
            int cnt = info[1];
            if (num == y)   return info[1];
            if (num+n <= y && !visited.contains(num+n)){
                q.add(new int[]{num+n, cnt+1});
                visited.add(num+n);
            }
            if (num*2 <= y && !visited.contains(num*2)){
                q.add(new int[]{num*2, cnt+1});
                visited.add(num*2);
            }
            if (num*3 <= y && !visited.contains(num*3)){
                q.add(new int[]{num*3, cnt+1});
                visited.add(num*3);
            }
        }
        return -1;
    }
}