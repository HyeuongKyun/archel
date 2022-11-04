import java.util.*;

class Solution {
    static char[] arr;
    static int N , answer;
    static boolean[] v;//순열을 구하기 위한 방문기록
    static List<Integer> list;
    static boolean[] primArr;
    static Set<Integer> set;
    
    public int solution(String numbers) {
        N = numbers.length();
        arr = numbers.toCharArray();
	        
        //풀이 방법 
        //조각들을 가장 큰 수로 만들고 나서 그 수만큼 배열을 만든다
        //그 배열에 체를 이용해서 합성수인 인덱스에는 true를 반환해서
        //조각들로 만들어질 수 있는 숫자를 순열을 통해 구하고
        //구한 수들을 인덱스로 이용해서 false 값들을 카운트 해준다.
	        
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();

        for(int i=N-1;i>=0;i--){
            sb.append(arr[i]);
        }

        int maxVal = Integer.parseInt(sb.toString());
        primArr = new boolean[maxVal+1];

        primArr[0] = true; 
        primArr[1] = true; //0과 1은 무조건 소수가 아니니 제외
        for(int n=2;n<=maxVal;n++){
            int idx = n;
            if (primArr[n]) continue;//이미 합성수임이 판명 났으면 그냥 패스
            while(true){
                idx += n; //처음 나온 수는 소수
                if(idx>maxVal) break;
                primArr[idx] = true;
            }//while
        }


        answer = 0;
        set = new HashSet<>();
        for(int i=1;i<=N;i++){
            v = new boolean[N];
            list = new ArrayList<>();
            permutation(0,i); //순열 돌릴 현재 인덱스, 길이가 1일 때 부터 N일때 까지의 nPr을 해서 모든 경우를 비교해줘야한다.


        }

        for(int i : set) {
            if(!primArr[i]) answer++;
        }
        
        // System.out.println(answer);
        return answer;

    }//main
    
    public static void permutation(int idx,int len){
        if(idx==len){
            StringBuilder sb2 = new StringBuilder();
            for(int i=0;i<len;i++){
                sb2.append(arr[list.get(i)]);
            }
            String temp = sb2.toString();
            int tempInt = Integer.parseInt(temp);

            set.add(tempInt);
            
            return;
        }//기저파트


        for(int i=0;i<N;i++) {
            if(v[i]) continue;
            v[i]=true;
            list.add(i);
            permutation(idx+1,len);
            v[i]=false;
            list.remove(list.size()-1);
        }

    }//permutation
    
}