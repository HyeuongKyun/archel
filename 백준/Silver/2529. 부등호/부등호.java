import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[] antiEquArr;
    static Long maxInt;
    static String maxStr;
    static Long minInt;
    static String minStr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        maxInt = Long.MIN_VALUE;
        minInt = Long.MAX_VALUE;


        int k = Integer.parseInt(st.nextToken());

        antiEquArr = new char[k];
        st = new StringTokenizer(br.readLine());
        for(int i=0 ;i<k;i++)
            antiEquArr[i] = st.nextToken().charAt(0);

//        for(int i=0;i<k;i++)
//            System.out.printf("%c ",antiEquArr[i]);
        int[] result = new int[k+1];
        boolean[] v = new boolean[10];
        boolean[] resV = new boolean[k+1];
        for(int i=0 ;i<=9;i++){
            result[0]=i;
            resV[0]=true; //0번째 채웠다는 의미
            v[i]=true;  //i값 썼다는 의미
            backTracking(1, k, result, v, resV);
            resV[0]=false;
            v[i]=false;
        }

        System.out.println(maxStr);
        System.out.println(minStr);
    }

    public static void backTracking(int idx, int k, int[] result, boolean[] v , boolean[] resV){
//        if(!resV[idx-1]) return; // 부등호를 만족 시키는 값을 못 넣은거니까 더 진행할 필요 x

        if(idx==k+1){
            StringBuilder sb = new StringBuilder();

            for(int i=0;i<=k;i++) sb.append(result[i]);
            Long tempRes = Long.parseLong(sb.toString());
            if(tempRes>maxInt) {
                maxInt = tempRes;
                maxStr = sb.toString();
            }
            if(tempRes<minInt) {
                minInt = tempRes;
                minStr = sb.toString();
            }

            return;
        }
        for(int i=0;i<=9;i++){
            if(v[i]) continue;
            if((antiEquArr[idx-1]=='>' && result[idx-1]>i || (antiEquArr[idx-1]=='<' && result[idx-1]<i))){
                result[idx]=i;
                resV[idx]= true;
                v[i]=true;
                backTracking(idx+1,k,result,v,resV);
                v[i]=false;
                resV[idx]=false;
            }
        }



    }
}
