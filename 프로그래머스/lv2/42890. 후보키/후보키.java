import java.util.*;

class Solution {
	
	ArrayList<HashSet<Integer>> candidateKeys;
	
	public int solution(String[][] relation){
		candidateKeys = new ArrayList<>();
		int n = relation[0].length;
		
		for(int i = 1 ; i <= n ; i++) { // 1~n개의 조합 생성
			combination(0, n, 0, i, new HashSet<>(), relation);
		}
		
		return candidateKeys.size();
	}

	private void combination(int start, int n, int idx, int size, HashSet<Integer> keys, String[][] relation) {
		if(idx == size) {
			for(HashSet<Integer> key : candidateKeys) {
				if(keys.containsAll(key)) { // 최소성 만족 X
					return;
				}
			}
			
			if(isUnique(keys, relation)) {
				candidateKeys.add(keys);
			} 
			return;
		}
		
		for(int i = start ; i < n ; i++) {
			HashSet<Integer> newKeys = new HashSet<>(keys);
			newKeys.add(i);
			combination(i+1, n, idx + 1, size, newKeys, relation);
		}
	}

	private boolean isUnique(HashSet<Integer> keys, String[][] relation) {
		HashSet<String> set = new HashSet<>();
		
		for(int r = 0 ; r < relation.length ; r++) {
			String key = "";
			
			for(int c : keys) {
				key += relation[r][c];
			}
			
			if(set.contains(key)) return false; // 유일성 만족X
			else set.add(key);
		}	
		return true;
	}
}