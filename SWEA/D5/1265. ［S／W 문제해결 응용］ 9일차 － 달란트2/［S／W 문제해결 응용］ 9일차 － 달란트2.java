import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=TC;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int talentum = Integer.parseInt(st.nextToken());
			int divide = Integer.parseInt(st.nextToken());
			
			int q = talentum/divide;
			int p = talentum%divide;
			
			long answer = (long) (Math.pow(q, divide-p)*Math.pow(q+1,p));
			
			System.out.printf("#%d %d\n",tc,answer);
			
		}//테스트 케이스
		
		br.close();
	}//main
}//class
