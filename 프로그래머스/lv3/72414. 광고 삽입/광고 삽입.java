import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int totalTime = timeToSec(play_time);
        int advTime = timeToSec(adv_time);
        long time[] = new long[totalTime+1];
        StringTokenizer st, sst;
        
        for(String log : logs){
            st = new StringTokenizer(log, "-");
            int startTime = timeToSec(st.nextToken());
            int endTime = timeToSec(st.nextToken());
            
            time[startTime] += 1;
            time[endTime] -= 1;
            
        }
        
        for (int i = 1; i <= totalTime; i++) time[i] += time[i - 1]; // i초에 시청한 사람 수
        for (int i = 1; i <= totalTime; i++) time[i] += time[i - 1]; // i초까지의 누적 시청 시간
        
        long maxTime = time[advTime - 1], maxStartTime = 0;
        for (int i = 0; i + advTime <= totalTime; i++) {
            long tmp = time[i + advTime] - time[i];

            if (tmp > maxTime) {
                maxTime = tmp;
                maxStartTime = i + 1;
            }
        }
                             
        return  String.format("%02d:%02d:%02d", maxStartTime / (60 * 60), (maxStartTime / 60) % 60, maxStartTime % 60);
    }
    
    static int timeToSec(String time){
        StringTokenizer st = new StringTokenizer(time, ":");
        return Integer.parseInt(st.nextToken()) * 3600 + Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }
}