package alchel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P9093 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int cnt = st.countTokens();
			
			for(int j=0;j<cnt;j++) {
				String str = st.nextToken();
				
				for(int k=str.length()-1;k>=0;k--) {
					sb.append(str.charAt(k));
				}
				
				sb.append(" ");
			}//for
			sb.append("\n");
			
		}//테스트 케이스
		
		System.out.println(sb);
		
		br.close();
	}//main
}//class
