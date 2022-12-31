//풀이방법 : dfs를 돌고 스펠링 하나 차이나는 곳은 재귀를 돌게한다. 재귀를 돌 때 방문기록을 v에 남겨서 거기에는 다시 안들어가게끔 한다.
//하다가 완전히 같은 경우가 나오면 그때까지 걸린 횟수를 리턴하게한다.
//만약 같은 경우가 없는데 재귀가 끝나면 실패니 -1을 리턴

//시간 복잡도 : 10개의 단어 길이를 50개의 재귀를 돌 돌안 검사한다.
// (50*51/2)*10 = 12,500 이게 한번 재귀를 최대로 돌 경우 여기에 2^50이 최대 생길 수 있는 경운데 1개 차이가 많이 없을것으로 예상 우선 해본다.
//되는 놈들 뽑아서 배열에 넣고 dfs 돌려본다
//우선 원하는 답이 words안에 없으면 0리턴은 굳이 안해도 되긴한다.(어차피 dfs실패하면 나올 값임)

import java.util.*;

class Solution {
    static List<Integer> list;
    static int N , n , minCnt;//N : words배열의 길이, n: word str의 길이 , 변환 횟수의 최소 횟수
    static boolean[] v;//방문한 곳 기록
    static String begin , target;
    static String[] words;
    public int solution(String begin, String target, String[] words) {
        this.begin = begin;
        this.target = target;
        this.words = words;
        list = new ArrayList<>();
        N = words.length;
        n = begin.length();
        boolean check = false;//굳이 안해도 bfs를 통해 걸러지지만 조금의 시간 단축을 위해
        //begin은 target에 있는 문자가 아니기 때문에 bfs를 돌기전에 먼저 값을 넣어주어야 한다.
        for(int i=0;i<N;i++){
            String word = words[i];
            if(word.equals(target)) check = true;
            int diff=0; // 글차가 몇 개 차이나는지 기록할 변수
            for(int j=0;j<n;j++){
                if(begin.charAt(j)!=word.charAt(j)) diff++;
            }//for j
            if (diff==1) {
                if(word.equals(target)) return 1;
                list.add(i);
            }//if
        }//for i
        if(!check) return 0;//target이 words에 없는 경우
        v = new boolean[N];
        minCnt = Integer.MAX_VALUE;
        find(1);
        
        if(minCnt == Integer.MAX_VALUE) return 0;//바꿀 방법이 없는 경우
        else return minCnt;
    }//solution
    private static void find(int cnt){
        List<Integer> listClone = new ArrayList<>();
        for(int i=0;i<list.size();i++)
            listClone.add(list.get(i));
        list = new ArrayList<>();
        // boolean[] vClone = new boolean[N];
        // for(int i=0;i<N;i++)
        //     vClone[i] = v[i];
        // v = new boolean[N];
        
        for(int l=0;l<listClone.size();l++){
            int tempIdx = listClone.get(l);
            String tempBegin = words[tempIdx];
            v[tempIdx] = true;
            for(int i=0;i<N;i++){
                //자기 자신과 이미 검사한 애를 또 검사하면 안되니까 continue;
                if(v[i]) continue;
                int diff=0;
                String word = words[i];
                for(int j=0;j<n;j++){
                    if(tempBegin.charAt(j)!=word.charAt(j)) diff++;
                }//for j
                if(diff==1){
                    if(target.equals(word)) {
                        if(cnt+1<minCnt) minCnt=cnt+1;
                        return;
                    }
                    list.add(i);
                }//if
            }//for i
            find(cnt+1);
            v[tempIdx] = false;
        }//for l
        
    } 
}