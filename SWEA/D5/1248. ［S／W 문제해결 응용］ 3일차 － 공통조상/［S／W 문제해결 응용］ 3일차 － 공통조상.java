import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N1,N2;//탐색할 node의 vertix번호
	static boolean firstBoolTrue;
	static Node[] arr;
	static Node result;//결과를 기록해줄 Node;
	static class Node{
		Node(int from){
			this.from = from;
		}
		int from = 0;//나중에 시작지점을 통해 편하게 답을 도출하기 위해서.
		List<Integer> nextVertix = new ArrayList<>();//다음 탐색할 vertix번호들
		int subtreeNum = 1;//서브트리의 갯수를 기록하기 위한 변수
		boolean vN1,vN2;//N1,N2를 들린 후면 true로 변환(후위탐색으로하면 공통부모를 찾을 수 있다.)
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=TC;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int vertixNum = Integer.parseInt(st.nextToken());
			int edgeNum = Integer.parseInt(st.nextToken());
			N1 = Integer.parseInt(st.nextToken());
			N2 = Integer.parseInt(st.nextToken());
			arr = new Node[vertixNum+1]; //정점 0인덱스 버림,그리고 이진트리기 때문에 최대 2개 말고는 더 값을 가지고 있지 않음
			
			for(int i=1;i<=vertixNum;i++) 
				arr[i] = new Node(i);
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0;i<edgeNum;i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				//from의 노드에 다음 vertix를 기록해둔다.
				arr[from].nextVertix.add(to);
				
			}//입력값 정리 끝
			
			result = null;
			firstBoolTrue = false;
			dfs(1);
			
			System.out.printf("#%d %d %d\n",tc,result.from,result.subtreeNum);
			
		}//테스트 케이스
		
		br.close();
	}//main

	private static void dfs(int start) {
		Node nowNode = arr[start];
		
		
		if(start==N1) nowNode.vN1 = true;
		else if(start==N2) nowNode.vN2 = true;
		
		
		int nowNodeLen = nowNode.nextVertix.size();
		List<Integer> list = nowNode.nextVertix;
		list.sort(null);//굳이 안해도 되는데 그냥한거
		for(int i=0;i<nowNodeLen;i++) {
			dfs(list.get(i));
			
		}
		
		for(int i=0;i<nowNodeLen;i++) {
			Node nextNode = arr[nowNode.nextVertix.get(i)];
			if(nextNode.vN1) nowNode.vN1=true;
			if(nextNode.vN2) nowNode.vN2=true;
			nowNode.subtreeNum += nextNode.subtreeNum;
		}
		
		
		if(nowNode.vN1 && nowNode.vN2 && !firstBoolTrue) {
			firstBoolTrue = true;
			result = nowNode;
			return;
		}
		
		
	}
}//class
