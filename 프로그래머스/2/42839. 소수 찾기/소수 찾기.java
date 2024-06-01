// number len 길이부터 1까지 어떤 순열로 뽑아서 생길 경우 모두 확인(중복되는 값은 경우의 수 구하는 경우 말고는 따로 방법이 없다.) 
// numbers로 구할 수 있는 가장 큰 수가 뭔지 보고 그거 기반으로 prime table 만들고
// 만들 수 있는 숫자들 리스트로 만들어서 테이블 비교해가며 갯수 카운팅하기 

import java.util.*;

class Solution {
    static char[] charArr;
    static Set<Integer> resultInt;
    static boolean[] primeTable;
    static int maxNum;
    static int count;
    public int solution(String numbers) {
        int len = numbers.length();
        resultInt = new HashSet<>();
        // charArr = new char[len];
        // maxNum = Integer.MIN_VALUE;
        maxNum = 5;
        count = 0;
        
        charArr = numbers.toCharArray();
        
        for(int i=1;i<=len; i++){
            char[] candiCharArr = new char[i];
            boolean[] candiBolArr = new boolean[len];
            putNum(0, i, candiCharArr, candiBolArr);
        }
        
        makePrimeTable();
        // System.out.println(resultInt);
        checkPrime();  // 명시적으로 resultInt 검사한다고 적어도 좋음
        
        int answer = count;
        return answer;
    }
    
    public void putNum(int idx, int len, char[] candiCharArr, boolean[] candiBolArr){
        if(idx==len){
            
            String res = String.valueOf(candiCharArr);
            // System.out.printf("res : %s\n",res);
            int resInt = Integer.valueOf(res);
            // System.out.printf("resInt : %d\n",resInt);
            if(resInt>maxNum) maxNum=resInt;
        
            resultInt.add(resInt);
            
            return;
        }
        
        for(int i=0;i<charArr.length;i++){
            if(!candiBolArr[i]){
                candiBolArr[i] = true;
                candiCharArr[idx] = charArr[i];
                putNum(idx+1, len, candiCharArr, candiBolArr);
                candiBolArr[i] = false;
            }
        }
    }
    
    public void makePrimeTable(){// 제일 큰 수의 절반까지 table에서 작은 수로 나누어지면 ture 변환 (즉 false가 소수)
        primeTable = new boolean[maxNum+1]; //다른 숫자도 소수인지 아닌지 판별해야하므로 전체 길이 만큼 표시해야함
        primeTable[0] = true; primeTable[1] = true; //0과 1은 소수가 아니므로 제외
        
        for(int idx=2;idx<maxNum/2;idx++){
            int d = 2;
            if(!primeTable[idx]){
                while(idx*d <= maxNum){
                    primeTable[idx*d++]=true;
                }
            }
        }
        
        // for(int i=0;i< maxNum+1 ;i++){
        //     if(i%10==0) System.out.println();
        //     System.out.printf("%b ", primeTable[i]);
        // }

        
    }
    
    public void checkPrime(){
        Iterator iter = resultInt.iterator();
        while(iter.hasNext()){
            //false인게 소수
            int temp = (int) iter.next();
            // System.out.println(temp);
            // System.out.println(primeTable[temp]);
            if(!primeTable[temp]) count++;
        }
    }
}