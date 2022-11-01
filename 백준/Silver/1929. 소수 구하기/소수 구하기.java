import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<>();
		boolean[] arr = new boolean[N+1];//0인덱스 버림
		
		for(int i=2; i<=N;i++) {
			if(!arr[i]) {
				list.add(i);
				int idx = i;
				while(idx<=N) {
					arr[idx] = true;
					idx += i;
				}//while
			}//if
		}//for
		
		for(int i=0;i<list.size();i++) {
			int temp = list.get(i);
			if(temp<M) continue;
			sb.append(temp+"\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}//main
}//class
