import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] arr,dp;
	static List<Integer> LIS;
	static int idx,incIdx;//기준이 되어줄 인덱스
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N+1];//0인덱스 버림
		for(int i=1;i<=N;i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		
		////////////////입력값 받기 끝
		
		dp = new int[N+1];
		LIS = new ArrayList<>();//LIS는 최대 N까지 들어가고 최장증가수열이 들어갈꺼기 때문에 List를 사용 배열로 해도 가능
		
		LIS.add(Integer.MIN_VALUE);//0인덱스 버림(밑에서 값비교를 편하게 해주기 위해서 0정수중 가장 작은 값을 넣어둠)
		idx=1;// 제일 처음 들어오는 수는 처리를 해줬으니까 그 다음 인덱스 부터 while문을 돌면서 비교를 해준다.
		incIdx =1;
		while(idx<=N) {
			if(arr[idx]>LIS.get(LIS.size()-1)) {
				dp[idx]=incIdx++;
				LIS.add(arr[idx]);
			}//if
			else {
				int num = arr[idx];
				int left = 1;
				int right = LIS.size()-1;
				
				while(left<right) {
					int mid = (left+right) >> 1; //(left+right)/2라고 생각하는데
					
					if(LIS.get(mid)>=num) right = mid;
					else left = mid +1;
				}//while 이분 탐색
				LIS.set(right, num);
				dp[idx] = right;
				
			}//else
			idx++;
		}//while
		
		sb.append(incIdx-1+"\n");
		List<Integer> list = new ArrayList<>();//뒤에서부터 뽑아줘야 해서 배열이 하나 필요하다.
		int maxIdx = incIdx-1;
		for(int i=arr.length-1;i>=1;i--) {
			if(maxIdx==dp[i]) {
				list.add(arr[i]);
				if(--maxIdx==0) break;//이미 뽑아낼 정보는 다 뽑아 냈으니까 더 하지말고 break;
			}
		}//for
		for(int i=list.size()-1;i>=0;i--) 
			sb.append(list.get(i)+" ");
		
		System.out.println(sb);
		br.close();
	}//main
}//class

