package alchel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P9012 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		n : for(int i=0;i<N;i++) {
			Stack<Character> stack = new Stack<>();
			String str = br.readLine();
			for(int j=0;j<str.length();j++) {
				char temp = str.charAt(j);
				if(temp=='(') {
					stack.add('(');
				}
				else {// ')'
					if(stack.isEmpty()) {
						sb.append("NO\n");
						continue n;
					}
					stack.pop();
				}
				
			}//for
			
			if(stack.isEmpty()) 
				sb.append("YES\n");
			else sb.append("NO\n");
		}//N¸¸Å­
		System.out.println(sb);
		br.close();
	}//main
}//class
