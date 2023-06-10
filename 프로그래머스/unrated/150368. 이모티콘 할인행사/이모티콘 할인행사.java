import java.util.*;

class Solution {
    //할인율이 0,10,20,30,40%니까 계속 확인할 때마다 계산하지말고 미리 구해놓고 쓰기
    static int[][] emoPrice, users, userPriceSum;
    static int maxUserCnt, maxBenefit;//최대 가입자 수, 최대 이득
    public int[] solution(int[][] users, int[] emoticons) {
        emoPrice = new int[emoticons.length][5];
        this.users = users;
        for(int i=0; i<emoticons.length; i++){
            for(int j=0; j<5; j++){
                int tempPrice = emoticons[i];
                tempPrice -= j* 0.1* tempPrice;
                emoPrice[i][j] = tempPrice;
            }//for j
        }//for i
        
        userPriceSum = new int[users.length][emoticons.length]; 
        maxUserCnt = 0;
        int[] percent = new int[emoPrice.length];
        
        //100명 * 4^7가지 경우 약 16,000,000 => 중복 순열로 풀어도 됌 
        //1부터 4까지 emoticons갯수만큼 뽑는다.
        permutation(0, percent);
        
        // System.out.printf("%d %d\n", maxUserCnt, maxBenefit);
        int[] answer = {maxUserCnt, maxBenefit};
        return answer;
    }//main
    
    public static void permutation(int idx, int[] percent){
        if(idx == emoPrice.length){
            // calMax(percent);
            // System.out.println(Arrays.toString(percent));
            // for(int k=0;k<userPriceSum.length;k++) {
            //     System.out.println(Arrays.toString(userPriceSum[k]));
            // }
            // System.out.println("--------------");
            int totalPrice = 0;
            int totalUserCnt = 0;
            for(int i=0;i<userPriceSum.length;i++){
            // System.out.printf("userPriceSum[i][emoPrice.length-1]: %d, users[i][1]:%d\n",userPriceSum[i][emoPrice.length-1] , users[i][1]);
                
                if(userPriceSum[i][emoPrice.length-1] >= users[i][1]) totalUserCnt++;
                else totalPrice += userPriceSum[i][emoPrice.length-1];
            }
            // System.out.printf("totalUserCnt: %d, totalPrice:%d\n",totalUserCnt, totalPrice);
            if(maxUserCnt < totalUserCnt) {
                maxUserCnt = totalUserCnt;
                maxBenefit = totalPrice;
            } else if(maxUserCnt == totalUserCnt && maxBenefit < totalPrice) maxBenefit = totalPrice;
            
            return;
        }//기저 조건
        
        for(int i=1; i<5; i++){
            //이러면 percent가 필요없긴하네
            percent[idx] = i;
            for(int j=0;j<userPriceSum.length;j++){
                // for(int k=0;k<users.length;k++) {
                //         System.out.println(Arrays.toString(users[k]));
                //     }
                if(i*10>=users[j][0]) {
                    if(idx==0) userPriceSum[j][idx] = emoPrice[idx][i];
                    else userPriceSum[j][idx] = userPriceSum[j][idx-1] + emoPrice[idx][i];
                    // for(int k=0;k<userPriceSum.length;k++) {
                    //     System.out.println(Arrays.toString(userPriceSum[k]));
                    // }
                } else {
                    if(idx==0) userPriceSum[j][idx] = 0;
                    else userPriceSum[j][idx] = userPriceSum[j][idx-1];
                }
                
            }
            permutation(idx + 1, percent);
//             for(int j=0;j<userPriceSum.length;j++){
//                 if(i*10>=users[j][0]) {
//                     if(idx==0) userPriceSum[j][idx] = 0;
//                     else userPriceSum[j][idx] -= emoPrice[idx][i];
//                 } else {
                    
//                 }
                
//             }
        }
    }//permutation
    
    // public static void calMax(int[] percent){
    //     int totalPrice = 0;
    //     for(int i=0;i<userPriceSum.length;i++){
    //         userPriceSume[i][]
    //     }
    // }
}