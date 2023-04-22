import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        StringTokenizer st;
		String time, carNum, info;
		ArrayList<Integer> tmpList = new ArrayList<>();

		HashMap<String, String> in = new HashMap<>();
		HashMap<String, String> out = new HashMap<>();
		HashMap<String, Integer> totalTime = new HashMap<>();
		HashMap<String, Boolean> check = new HashMap<>();
		TreeMap<String, Integer> sortedFee = new TreeMap<>();

		for (String s : records) {
			st = new StringTokenizer(s);
			time = st.nextToken().replace(":", "");
			carNum = st.nextToken();
			info = st.nextToken();

			if (info.equals("IN")) {
				in.put(carNum, time);
				check.put(carNum, false);
			} else {
				out.put(carNum, time);
				check.put(carNum, true);

				calculateTime(fees, in, out, totalTime, carNum);

			}
		}

		for (String car : in.keySet()) {
			if (!check.get(car)) { // 출차 기록이 없다면
				out.put(car, "2359");
				calculateTime(fees, in, out, totalTime, car);
			}
		}

		for (String car : totalTime.keySet()) {
			int fee = fees[1];
			if (totalTime.get(car) > fees[0]) {
				int resTime = totalTime.get(car) - fees[0];
				fee += (int)Math.ceil(resTime*1.0/fees[2]) * fees[3];
			}
	//		System.out.printf("차번호 : %s 총시간 : %d, 요금 %d\n", car, totalTime.get(car), fee);

			sortedFee.put(car, fee);
		}

		while (!sortedFee.isEmpty()) {
			tmpList.add(sortedFee.pollFirstEntry().getValue());
		}

		int[] answer = new int[tmpList.size()];
		int size = 0;
		for (int f : tmpList)
			answer[size++] = f;

		return answer;

	}

	static void calculateTime(int fees[], HashMap<String, String> in, HashMap<String, String> out,
			HashMap<String, Integer> totalTime, String carNum) {

		int startHour = Integer.parseInt(in.get(carNum).substring(0, 2));
		int startMin = Integer.parseInt(in.get(carNum).substring(2));
		int endHour = Integer.parseInt(out.get(carNum).substring(0, 2));
		int endMin = Integer.parseInt(out.get(carNum).substring(2));

		int time = (endHour - startHour) * 60 + (endMin - startMin);

		if (totalTime.containsKey(carNum)) {
			totalTime.put(carNum, totalTime.get(carNum) + time);
		} else {
			totalTime.put(carNum, time);
		}

	}
}