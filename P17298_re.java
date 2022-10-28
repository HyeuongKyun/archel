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
		int idx,val,goal;//���� �� ��, NGE ��
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
		arr = new Node[N+1];//0�ε��� ����
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			int temp = Integer.parseInt(st.nextToken());
			Node node = arr[i] = new Node(i,temp,0);//�켱�� �ٷ� NGE ���� �� �� �����ϱ� 0���� �ִ´�.
			
			if(stack.isEmpty()) stack.add(node);
			else {
				while (!stack.isEmpty() && stack.peek().val<node.val) {
					Node preNode = stack.pop();
					preNode.goal = node.val;
				}//while
				
				//���� ������ ���� ������ ������ ũ�� �۵� �־�����Ѵ�.
				stack.add(node);
			}//if else
			
		}
		
		while(!stack.isEmpty()) {
			Node preNode = stack.pop();
			preNode.goal = -1;
		}//�ȿ� �ִ� �༮�� ���� ����ֱ�
		
		for(int i=1;i<=N;i++) {
			sb.append(arr[i].goal + " ");
		}
		
		System.out.println(sb);
		
		br.close();
	}//main

}//class
