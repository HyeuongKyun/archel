package alchel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class P17298_false {
	static int N;
	static Stack<Integer> stack;
	static int[] totalArr;
	static int[] order;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		order = new int[N+1];//0인덱스 버림 몇번째로 들어온 값인가 기억해주기 위해서 만든 배열
		stack = new Stack<>();
		totalArr = new int[1000000+1];//1부터 1,000,000까지 각 인덱스가 가지고 있을 NGE값
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			int temp = Integer.parseInt(st.nextToken());
			order[i] = temp;
			if(stack.isEmpty()) stack.add(temp);
			else {
				while (!stack.isEmpty() && stack.peek()<temp) {
					int pop = stack.pop();
					totalArr[pop] = temp;
				}//while
				
				//새로 들어오는 값이 이전의 값보다 크든 작든 넣어줘야한다.
				stack.add(temp);
			}//if else
			
		}
		
		while(!stack.isEmpty()) {
			int pop = stack.pop();
			totalArr[pop] = -1;
		}//안에 있는 녀석들 마저 비워주기
		
		for(int i=1;i<=N;i++) {
			sb.append(totalArr[order[i]] + " ");
		}
		
		System.out.println(sb);
		
		br.close();
	}//main

}//class
