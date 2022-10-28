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
		order = new int[N+1];//0�ε��� ���� ���°�� ���� ���ΰ� ������ֱ� ���ؼ� ���� �迭
		stack = new Stack<>();
		totalArr = new int[1000000+1];//1���� 1,000,000���� �� �ε����� ������ ���� NGE��
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
				
				//���� ������ ���� ������ ������ ũ�� �۵� �־�����Ѵ�.
				stack.add(temp);
			}//if else
			
		}
		
		while(!stack.isEmpty()) {
			int pop = stack.pop();
			totalArr[pop] = -1;
		}//�ȿ� �ִ� �༮�� ���� ����ֱ�
		
		for(int i=1;i<=N;i++) {
			sb.append(totalArr[order[i]] + " ");
		}
		
		System.out.println(sb);
		
		br.close();
	}//main

}//class
