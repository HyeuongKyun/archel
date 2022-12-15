// 풀이방법
//우선 k진수로 표현하는데 n이 1,000,000까지 이므로 k=3 일 때 가장 많은 block이 필요하다. 2 × 3^12= 2×500,000=1,000,000
//아무리 많이 나와도 12자리를 안넘는다.
//그러면 그런데 이게 10진법으로 나타냈을 때는 10^12까지 나오게 되면 체(소수) 구하는게 너무 오래 걸리기 때문에 체를 그냥 구하는게 아니라 k에 맞춰 k초과하는 부분에 체는 그냥 if문으로 통과한다. 그냥 해도 될꺼 같기도하고
//그렇게 체를 구하고나면 P 조건에 맞는 소수를 찾아야한다.
//=> 이건 0이 이 아닌 녀석을 StringBuilder로 합치고 0이 나오면 소수 비교 후 초기화 해준다.
import java.util.*;

class Solution {
    static int N,n,k,answer; //k진수를 표현하기 위한 배열의 길이
    static int[] arrK; //k진수를 표현하는 배열
    static boolean[] filed;
    static int filedSize;
    public int solution(int n, int k) {
        this.n=n;
        this.k=k;
        
        //n을 k진수로 표현하기 위해 몇 최소 몇 개의 N가 필요한지 조사
        N = getLen(n,k);
        arrK = new int[N];
        
        //K진수로 배열 채워 넣어주기
        fillArrK();
        
        //소수인지 판별하기 위한 체 만들어 두기
        // filedSize = k*((int)Math.pow(10,N-1));//사실 좀 더 범위를 줄일 수 있다.
        // filed = new boolean[filedSize];
        // makeFiled();
        //필드를 만드는 비용이 너무 많이 드니까 그냥 숫자가 등장할 때마다 소수인지 확인하자
        
        //배열에서 소수 뽑아내기
        answer = 0;
        getPrime();
        
        // System.out.println(Arrays.toString(arrK));
        return answer;
    }//main
    //여기서 시간초과 날꺼같다. 나겠다.
    private static void getPrime(){
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        //제일 앞에 인덱스는 무조건 0이 아니므로 그냥 담고 시작해도 된다.
        while(idx<N){
            int tempVal = arrK[idx];
            if(tempVal!=0){
                sb.append(String.valueOf(tempVal));
            } 
            else {
                // System.out.println("not Run");
                String str = sb.toString();
                if(!str.equals("")){
                    // System.out.println(str);
                    //다음 조사를 위해 초기화
                    sb = new StringBuilder();
                    double val = Double.parseDouble(str);
                    // if(filed[val]) answer++;
                    checkPrime(val);
                }//if 만약 str에 아무 값도 없다면 그냥 지나간다.
            }//if else
            // System.out.println(str);
            idx++;
            String str = sb.toString();
            if(idx==N && !str.equals("")){
                double val = Double.parseDouble(str);
                checkPrime(val);
            }
        }//while
        
    }//getPrime
    //제곱근 판별법
    private static void checkPrime(double val){
        
        if (val==2) {
            answer++;
            return;
        }
        else if(val>2){
            for(int i=3;i<=Math.sqrt(val);i+=2){
                if(val%i==0) return;
            }//for
            answer++;
        }//if else if
    }//checkPrime
    
    // private static void makeFiled(){
    //     for(int i=2;i<filedSize;i++){
    //         if(!filed[i]){
    //             int ele=i;
    //             for(int j=i;j<filedSize;i+=ele){
    //                 filed[j]=true;
    //             }//for
    //         }//if
    //     }
    // }//makeFiled
    private static void fillArrK(){
        int tempN = n;
        int q = 15; //어차피 인덱스의 최대는 13이기 때문에 최대값으로 초기화
        int idx = N-1;
        //예시 k=3 , n=33으로 해보기 학창시절에 배운 k진수 나타내는 방법을 사용한다.
        while(true){
            q = tempN/k;
            int r = tempN%k;
            //배열에 값이 들어가는게 마무리 되는 조건
            arrK[idx--] = r;
            if(q<k){
                arrK[idx] = q;
                break;
            }//if
            tempN = q;
        }//while
    }//fillArrK
    
    private static int getLen(int n, int k){
        int num=0;
        int tempN=-1;
        while(num<n){
            tempN++;
            //num은 tempN 인덱스에 나타낼 수 있는 10진수 값을 의미
            num += (k-1) * ((int) Math.pow(k,tempN));
        }//while
      
        //배열의 길이가 필요한거 이므로 +1을 해줘야한다.
        return tempN+1;
    }
}