class Solution {
    public int solution(int storey) {
        int answer = 0;
        while(storey>0){
            int num = storey % 10; // 마지막자리수
            storey /= 10; 
            if (num < 5){ // -연산
                answer += num;
            } else if (num > 5) { // +연산 후 앞자리 숫자 바뀜
                answer += (10-num);
                storey++;
            } else if (storey % 10 >= 5) { // num이 5이고, 그 앞자리 숫자가 5이상인 경우는 storey를 증가시키는 것이 빠른 연산이다.
                answer += 5;
                storey++;
            } else { // num이 5이고, 앞자리 숫자가 5보다 작은 경우, storey를 -연산으로 작게 만든다.
                answer += 5;
            }
        }
        return answer;
    }
}