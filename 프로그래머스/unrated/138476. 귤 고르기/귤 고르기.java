import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        PriorityQueue<Tangerine> q = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : tangerine){
            map.put(t, map.getOrDefault(t,0)+1);
        }
        for(int t: map.keySet()){
            q.add(new Tangerine(t, map.get(t)));
        }
        while(k > 0){
            Tangerine t = q.poll();
            k -= Math.min(k, t.count);
            answer++;
        }
        return answer;
    }
    
    static class Tangerine implements Comparable<Tangerine>{
        int weight;
        int count;
        
        public Tangerine(int weight, int count){
            this.weight = weight;
            this.count = count;
        }
        
        @Override
        public int compareTo(Tangerine t){
            return t.count - this.count;
        }
    }
}