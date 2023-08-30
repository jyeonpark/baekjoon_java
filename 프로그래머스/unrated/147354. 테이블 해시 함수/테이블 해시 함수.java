import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        int length = data[0].length;
        Arrays.sort(data, (arr1, arr2) -> {
            if (arr1[col-1] == arr2[col-1]){
                return arr2[0] - arr1[0];
            }
            return arr1[col-1] - arr2[col-1];
        });
  
        for(int i=row_begin-1; i<row_end; i++){
            int si = 0;
            for(int j=0; j<length; j++){
                si += data[i][j] % (i+1);
            }
            if (i == row_begin-1)   answer = si;
            else {
                answer = answer ^ si;
            }
        }
        
        return answer;
    }
}