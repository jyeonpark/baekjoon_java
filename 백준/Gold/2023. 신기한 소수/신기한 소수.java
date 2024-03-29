import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 신기한 소수 : 에라토스테네스의 체 => 시간 초과!!
 * @author SSAFY
 *
 */
public class Main {

	static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int arr[] = {2,3,5,7};
		for(Integer i : arr)
			recur(1,i);
		
	}
	
	public static void recur(int length, int num) {
		if (length == n) {
			System.out.println(num);
			return;
		}
		
		for(int i=1; i<=9; i++) {
			int new_num = num*10 + i; // 한자리 덧붙히기
			if (isPrime(new_num)) { // 만약 해당수가 소수라면
				recur(length+1, new_num); // 붙여서 재귀호출
			}
		}
	}
	
	public static boolean isPrime(int num) {

		for(int i=2; i<=Math.sqrt(num); i++) {
			if (num%i == 0)
				return false;
		}
		return true;
	}

}
