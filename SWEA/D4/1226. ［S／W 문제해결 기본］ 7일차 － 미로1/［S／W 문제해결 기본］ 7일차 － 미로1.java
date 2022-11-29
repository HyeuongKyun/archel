import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static char[][] map;
	static boolean[][] v;//0인곳 방문기록
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc=1;tc<=10;tc++) {
			br.readLine();
			map = new char[16][16];
			for(int i=0;i<16;i++) 
				map[i] = br.readLine().toCharArray();
			//입력값 받기 끝
			
			
			//3을 찾기 위한 여정.
			//만약 3이 dfs를 돌면서 못찾는다면 0을 반환 3을 찾으면 1을 반환
			v = new boolean[16][16];
			result = 0;
			dfs(1,1);
			
			System.out.printf("#%d %d\n",tc,result);
			
		}//테스트 케이스
		
		br.close();
	}

	private static void dfs(int row, int col) {
		if(map[row][col]=='3') {
			result=1;
			return;
		}

		for(int d=0;d<4;d++) {
			int nr = row +dr[d];
			int nc = col +dc[d];
			//주변이 항상 1로 경계가 둘러져 있어서 경계에 대한 신경은 안써도 된다.
			
			if(map[nr][nc]!='1' && !v[nr][nc]) {
				v[nr][nc]=true;
				dfs(nr,nc);
			}//if
			
		}//for
		
	}//dfs
}
