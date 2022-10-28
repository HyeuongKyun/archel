package alchel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class P17298_re {
	static int N;
	static Stack<Node> stack;
	static Node[] arr;
	static StringBuilder sb = new StringBuilder();
	
	static class Node {
		int idx,val,goal;//현재 내 값, NGE 값
		public Node(int idx, int val, int goal) {
			this.idx = idx;
			this.val = val;
			this.goal = goal;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stack = new Stack<>();
		arr = new Node[N+1];//0인덱스 버림
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			int temp = Integer.parseInt(st.nextToken());
			Node node = arr[i] = new Node(i,temp,0);//우선은 바로 NGE 값을 알 수 없으니까 0으로 넣는다.
			
			if(stack.isEmpty()) stack.add(node);
			else {
				while (!stack.isEmpty() && stack.peek().val<node.val) {
					Node preNode = stack.pop();
					preNode.goal = node.val;
				}//while
				
				//새로 들어오는 값이 이전의 값보다 크든 작든 넣어줘야한다.
				stack.add(node);
			}//if else
			
		}
		
		while(!stack.isEmpty()) {
			Node preNode = stack.pop();
			preNode.goal = -1;
		}//안에 있는 녀석들 마저 비워주기
		
		for(int i=1;i<=N;i++) {
			sb.append(arr[i].goal + " ");
		}
		
		System.out.println(sb);
		
		br.close();
	}//main

}//class
