import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for(int e : enemy){
            q.add(e);
            n -= e;
            answer++;
            if (n < 0) {
                if (k > 0){ // 무적권 사용
                    k--;
                    n += q.poll();
                } else {
                    return answer-1;
                }
            }
        }
        return enemy.length;
    }
}