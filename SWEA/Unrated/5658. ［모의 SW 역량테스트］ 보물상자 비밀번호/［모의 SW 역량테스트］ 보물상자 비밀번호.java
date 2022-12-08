import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static Set<String> stringSet;
    static String str;
    static StringBuilder sbNumber;
    static int quotient,N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for(int tc=1;tc<=TC;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());//입력받을 String의 길이
            int K = Integer.parseInt(st.nextToken());//내림차순 N번째 자리 숫자
            str = br.readLine();
            stringSet = new HashSet<>();

            //str을 4등분해서 각자의 string으로 만들어 주기
            sbNumber = new StringBuilder();
            for(int i=0;i<N;i++){
                String tempStr = String.valueOf(str.charAt(i));
                quotient = N/4;
                if(i%quotient==0){
                    sbNumber = new StringBuilder();
                    sbNumber.append(tempStr);
                } else {
                    sbNumber.append(tempStr);
                }
                if(i%quotient==quotient-1){
                    String originStr = sbNumber.toString();
                    stringSet.add(originStr);
                    //이제 부터 슬라이드 형식으로 밀어주면서 생길 수 있는 경우를 모두 확인 해 줄 것이다.
                    //몫만큼 반복하게 되면 마지막은 originStr이랑 같으니까 마지막 한번은 안해줘도 된다.
                    for(int j=1;j<quotient;j++){
                        rotation(i,j);
                    }
                }
            }//for

            //반복문을 모두 돌면서 나올 수 있는 모든 경우를 set에 넣었다.

            List<String> list = new ArrayList<>(stringSet);
            Collections.sort(list,Collections.reverseOrder());
            String result = list.get(K-1);//K번째로 큰 값 뽑기
            int answer = 0;
            for(int i=0;i<result.length();i++){
                //16진수를 10진수로 바꾸기 메서드를 모름
                char tempChr = result.charAt(i);
                 int resultPosition =  result.length()-1-i;
                if(tempChr-'0'>10){//알파벳 대문자인 경우
                    answer += (tempChr-'7')*((int) Math.pow(16,resultPosition));
                } else { //숫자인 경우
                    answer += (tempChr-'0')*((int) Math.pow(16,resultPosition));
                }
            }

            //이제 answer에 10진수로 바꾼 정답이 들어갔으니 sb에 넣고 다음 테스트 케이스 실행

            sb.append("#" + tc + " " + answer + "\n");
        }//테스트 케이스
        System.out.println(sb);
        br.close();
    }//main
    static private void rotation(int nowIdx,int count){
        //제일 뒷부분에 있는 걸 삭제 해주고 앞에서 밀어 넣어준다.
        sbNumber.delete(quotient-1,quotient);
        StringBuilder rotaSbNumber = new StringBuilder();
        int newValIdx = nowIdx-quotient-count+1;
        if(newValIdx<0) newValIdx += N;
        String tempStr = String.valueOf(str.charAt(newValIdx));
        rotaSbNumber.append(tempStr).append(sbNumber);
        sbNumber = rotaSbNumber;
        stringSet.add(String.valueOf(sbNumber));
    } //rotation
}//class
