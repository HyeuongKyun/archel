//풀이 방법: 2번째로 생각해낸 방법과 유사하지만 다른 점은 dfs 사용하여 완전 탐색을 했다는 점이다.
// 그런데 완전 탐색을 하는데 다하는 것이 아니라 필요없는 경우를 줄인다.
//필요 없이 dfs를 돌아 시간초과 내는 이유 len은 6밖에 안되는데 바꾸는 횟수가 10이라 dfs를 10돌아버리면 시간이 훨씬 오래 걸리게 된다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for(int tc=1;tc<=TC;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String number = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            char[] numbers = number.toCharArray();
            result=0;

            //최대 교환 횟수는 총 숫자를 넘기 않게하고 dfs를 돌리는데 이 이유는
            //안해줘도 값은 똑같지만 시간이 시간초과가 뜨고 한 10배정도 차이가 난다.
            //어차피 뒤는 안해줘도 된다는 의미인거 같은데 만약에 테케가 456789에 9로 들어왔으면 틀렸다.
            //=> 즉 그 의미는 cnt까지만 해주고 나서 남은 수가 홀수냐 짝수냐에 따라서 맨 뒷자리 값을 바꿀지 안바꿀지 정해주면된다.
            if(numbers.length<cnt) cnt = numbers.length;
            dfs(cnt,0,numbers);

            System.out.printf("#%d %d\n",tc,result);

        }//테스트 케이스

        br.close();
    }//main

    private static void dfs(int cnt, int start, char[] numbers) {
        if(cnt==0){
            //꼭 n번이 시행되어야만 result가 갱신되기 때문에 중간에 아무리
            //최대값이 나왔다고 하더라도 갱신되지 않는다
            //따라 92 1 => 29 , 92 2 => 92 , 92 3 => 29
            int current = Integer.parseInt(new String(numbers));


            if(current>result) result = current;
            return;
        }//기저

        //구간을 이렇게 나누어야 중복이 생기는 경우를 막을 수 있다 그래서 모든 경우를 효율 적으로 돌 수 있다.
        //순서가 상관 없는게 아니라 순서가 상관은 있지만 다른 경로로 대처가능하다.
        // ex 2 8 4 =>은 8과4를 바꾸고 2와 8을 바꾸는게 최대지만 이걸 다른 경로인
        // 2와8을 바꾸고 2와 4를 바꾸는 경우와 같다.(이 방법으로 시간 복잡도를 낮춘다.)
        for(int i=start;i<numbers.length-1;++i){
            for(int j=i+1;j<numbers.length;++j){
                int src = Integer.parseInt(String.valueOf(numbers[i]));
                int trg = Integer.parseInt(String.valueOf(numbers[j]));

                numbers[i] = (char)(trg+'0');
                numbers[j] = (char)(src+'0');
                dfs(cnt-1,i,numbers);
                numbers[i] = (char)(src+'0');
                numbers[j] = (char)(trg+'0');

            }
        }

    }//dfs
}//class
