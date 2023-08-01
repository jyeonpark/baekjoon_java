import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        answer[0] = 0;
        answer[1] = Integer.MAX_VALUE;
        int n = sequence.length;
        int left = 0; 
        int right = 0;
        int sum = sequence[0]; 
        while(left<=right && right<n){
            if (sum < k){
                right++;
                if (right < n)  sum += sequence[right];
            }
            else if (sum > k){
                sum -= sequence[left];
                left++;
            }
            else if (sum==k){
                if (answer[1] - answer[0] > right - left){
                    answer[1] = right;
                    answer[0] = left;
                }
                sum -= sequence[left];
                left++;
            }
        }
        return answer;
    }
}