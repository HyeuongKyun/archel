class Solution {
    boolean solution(String s) {
        int pCnt=0;
        int yCnt=0;
        
        for(int i=0;i<s.length();i++){
            char nowChar = s.charAt(i);
            if(nowChar =='p' || nowChar =='P') pCnt++;
            if(nowChar =='y' || nowChar =='Y') yCnt++;
            
        }
        if(pCnt==yCnt) return true;
        else return false;
    }
}