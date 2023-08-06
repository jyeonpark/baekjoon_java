import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int[][] weights = {{1,1,1},{5,1,1},{25,5,1}};
        int answer = 0;
        int pickCnt = Arrays.stream(picks).sum();
        // 광물을 5개씩 묶은 그룹들을 각 그룹의 광물의 가중치 합의 내림차순에 따라 정렬한 큐
        // 다이아몬드->1 철->5 돌->25
        PriorityQueue<Group> groups = new PriorityQueue<>(Collections.reverseOrder()); 
        for(int i=0; i<minerals.length; i+=5){
            Group g = new Group();
            g.list = new ArrayList<Integer>();
            for(int j=i; j<(i+5); j++){
                if (j>= minerals.length)    break;
                if (minerals[j].equals("diamond"))  {
                    g.list.add(0); // 다이아몬드 광물
                    g.total += 25;
                }
                else if(minerals[j].equals("iron")) {
                    g.list.add(1); // 철 광물
                    g.total += 5;
                }
                else {
                    g.list.add(2); // 돌 광물
                    g.total += 1;
                }
            }
            groups.add(g);
            if (groups.size() == pickCnt)   break; // 곡괭이 개수만큼 그룹을 만들었다면, 이후의 미네랄에 대해서는 캘 수 없다.
        }
        while(!groups.isEmpty()){
            Group g = groups.poll();
            int pick;
            if (picks[0] > 0){ // 다이아몬드 곡괭이 사용
                picks[0]--;
                pick = 0;
            }
            else if (picks[1] > 0){ // 철 곡괭이 사용
                picks[1]--;
                pick = 1;
            }
            else if (picks[2] > 0){ // 돌 곡괭이 사용
                picks[2]--;
                pick = 2;
            }
            else{ // 곡괭이를 모두 사용한 경우
                break;
            }
            for(int m : g.list){
                answer += weights[pick][m];
            }
        }
        return answer;
    }
    static class Group implements Comparable<Group>{
        int total;
        ArrayList<Integer> list;
        
        @Override
        public int compareTo(Group g){
            return this.total - g.total;
        }
    }
}