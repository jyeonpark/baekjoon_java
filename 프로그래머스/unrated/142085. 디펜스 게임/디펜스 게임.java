import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        int len = enemy.length;
        int i = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        while(true){
            if (i == len){
                answer = i;
                break;
            }
            int num = enemy[i];
            if (n >= num){
                n -= num;
                q.add(num);
                i++;
            }
            else if (k > 0 && !q.isEmpty()){ 
                if (q.peek() >= num){ // 앞숫자에서 무적권 사용
                    n += q.poll();
                    k--;
                } else{
                    k--;
                    i++;
                }
            }
            else if (k>0 && q.isEmpty()) {
                k--;
                i++;
            } else{
                answer = i;
                break;
            }
        }
        return answer;
    }
}