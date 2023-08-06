import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] books = new int[book_time.length][2];
        PriorityQueue<Integer> q = new PriorityQueue<>();
        Arrays.sort(book_time, (b1,b2)->b1[0]==b2[0] ? b1[1].compareTo(b2[1]) : b1[0].compareTo(b2[0]));
        q.add(convertTime(book_time[0][1]));
        for(int i=1; i<book_time.length; i++){
            int startTime = convertTime(book_time[i][0]);
            int endTime = convertTime(book_time[i][1]);
            if (startTime >= q.peek()+10){ // 같은 방을 퇴실하고 쓸 수 있는 경우
                q.poll();
                q.add(endTime);
            } else{ // 새로운 방을 써야 하는 경우
                q.add(endTime);
            }
        }
        return q.size();
    }
    
    static int convertTime(String s){
        StringTokenizer st = new StringTokenizer(s, ":");
        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());
        return hour*60 + min; 
    }
}