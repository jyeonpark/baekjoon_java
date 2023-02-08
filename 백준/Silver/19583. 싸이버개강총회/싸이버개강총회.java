import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Map<String, Integer> map = new HashMap<String, Integer>();


		// 개강총회 시작 시간
		String opening_start = st.nextToken();

		// 개강총회 끝 시간
		String opening_end = st.nextToken();

		// 개강총회 스트리밍 끝 시간
		String streaming_end = st.nextToken();

		String line = "";
		while ((line = br.readLine()) != null) {
			st = new StringTokenizer(line, " ");
			String time = st.nextToken();
			String nickname = st.nextToken();

			if (time.compareTo(opening_start) <= 0) { // 입장
				map.put(nickname, 1);
			}

			if (time.compareTo(opening_end) >= 0
					&& time.compareTo(streaming_end) <= 0) { // 종료
				if (map.containsKey(nickname) && map.get(nickname) == 1) { // 입장을 한 회원이라면
					map.put(nickname, 2);
				}
			}
		}

		int cnt = 0;
		for (Integer i : map.values()) {
			if (i == 2)
				cnt++;
		}
		System.out.println(cnt);
	}

}
