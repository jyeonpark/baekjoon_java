import java.util.*;

class Solution {
    static long answer;
    static Map<Double, Integer> map;
    public long solution(int[] weights) {
        int n = weights.length;
        map = new HashMap<>();
        Integer[] arr = Arrays.stream(weights).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Collections.reverseOrder());
        for(int weight: weights){
            map.put(weight*1.0, map.getOrDefault(weight*1.0, 0) + 1);
        }
        for(int weight: arr){
            map.put(weight*1.0, map.get(weight*1.0)-1);
            answer += map.getOrDefault(weight*1.0,0);
            answer += map.getOrDefault(weight*3.0/4,0);
            answer += map.getOrDefault(weight*1.0/2,0);
            answer += map.getOrDefault(weight*2.0/3,0);
        }
        return answer;
    }
}
    