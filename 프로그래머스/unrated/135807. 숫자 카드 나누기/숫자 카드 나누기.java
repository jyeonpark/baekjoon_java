import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = Math.max(findDivisor(arrayA, arrayB), findDivisor(arrayB, arrayA)); 
        return answer;
    }
    
    static int findDivisor(int[] arrayA, int[] arrayB){
        Arrays.sort(arrayA);
        PriorityQueue<Integer> div = new PriorityQueue<>(Collections.reverseOrder());
        int num = arrayA[0];
        int sqrt = (int) Math.sqrt(num);
        for(int i=1; i<=sqrt; i++){
            if (num % i == 0){
                div.add(i);
                if (num / i != i){
                    div.add(num/i);
                }
            }
        }
        while(!div.isEmpty()){
            int x = div.poll(); // 가장 큰 약수부터 체크한다.
            if (isDivided(x, arrayA) && isNotDivided(x, arrayB)) return x;
        }
        return 0;
    }
    
    static boolean isDivided(int x, int[] arr){
        // arr 의 모든 숫자를 나눌 수 있다
        for(int i : arr){
            if (i % x != 0) return false;
        }
        return true;
    }
    
    static boolean isNotDivided(int x, int[] arr){
        // arr 의 모든 숫자를 나눌 수 없다.
        for(int i : arr){
            if (i % x == 0) return false;
        }
        return true;
    }
}