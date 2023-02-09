import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int s_len;
	static int p_len;
	static char[] str;
	static int[] checkArr = new int[4];
	static int[] myArr = new int[4];
	static int checkCnt = 0; // {‘A’, ‘C’, ‘G’, ‘T’} 중 최소 개수를 일치한 문자 개수 (0~4)
	static int answer = 0; // 만들 수 있는 비밀번호 개수

	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		s_len = Integer.parseInt(st.nextToken());
		p_len = Integer.parseInt(st.nextToken());
		str = br.readLine().toCharArray(); // DNA 문자열
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 4; i++) {
			checkArr[i] = Integer.parseInt(st.nextToken());
			if (checkArr[i] == 0)
				checkCnt++;
		}

		for (int i = 0; i < p_len; i++) { // 첫 부분문자열 셋팅 (0~p_len-1) 까지
			add(str[i]);
		}
		
		if (checkCnt >= 4) { // {‘A’, ‘C’, ‘G’, ‘T’} 4개의 문자가 모두 최소개수를 만족했다면
			answer++; // 만들 수 있는 비밀번호 개수 증가
		}
	

		int i = -1;
		/**
		 * 부분문자열 만들기 => 슬라이딩 윈도우 이전 부분문자열의 첫 문자는 제외하고, 끝에서 1문자를 더 추가한다.
		 */
		for (int j = p_len; j < s_len; j++) { // 부분문자열의 끝을 나타내는 위치
			i = j - p_len; // 이전 부분문자열의 시작을 나타내는 위치
			remove(str[i]); // 한칸 오른쪽으로 이동하므로, 이전 부분문자열의 시작은 제외됨
			add(str[j]); // 한칸 오른쪽으로 이동하므로, 이전 부분문자열의 끝에서 1문자가 더 추가됨
			if (checkCnt >= 4) { // {‘A’, ‘C’, ‘G’, ‘T’} 4개의 문자가 모두 최소개수를 만족했다면
				answer++; // 만들 수 있는 비밀번호 개수 증가
			}
//			System.out.println(Arrays.toString(myArr));
		}

		System.out.println(answer);

	}

	static void add(char c) {

		switch (c) {
		case 'A':
			myArr[0]++;
			if (myArr[0] == checkArr[0])
				checkCnt++;
			break;
		case 'C':
			myArr[1]++;
			if (myArr[1] == checkArr[1])
				checkCnt++;
			break;
		case 'G':
			myArr[2]++;
			if (myArr[2] == checkArr[2])
				checkCnt++;
			break;
		case 'T':
			myArr[3]++;
			if (myArr[3] == checkArr[3])
				checkCnt++;
			break;
		default:
			break;
		}

	}

	static void remove(char c) {

		switch (c) {
		case 'A':
			if (myArr[0] == checkArr[0])
				checkCnt--;
			myArr[0]--;
			break;
		case 'C':
			if (myArr[1] == checkArr[1])
				checkCnt--;
			myArr[1]--;
			break;
		case 'G':
			if (myArr[2] == checkArr[2])
				checkCnt--;
			myArr[2]--;
			break;
		case 'T':
			if (myArr[3] == checkArr[3])
				checkCnt--;
			myArr[3]--;
			break;
		default:
			break;
		}

	}

}
