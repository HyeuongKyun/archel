//풀이 방법
//Map<String,Integer>을 만들어서 의상의 종류에 대해 getOrDefault를 통해 갯수를 수정해주고
//그러고나서 map의 원소가 그 옷장에 있는 옷 종류 그 값들이 각 그 의상에 해당하는 갯수가 된다.
//그렇게 하고나서 부턴 수학
//1개 이상만 입으면 되니까 하나씩만 입었을 때 모든 map의 value를 더하고
//2개 입는 날에는 map의 size에  size C 2를 해서 그 원소들의 곱 더하고
//... size개 입는 날에는 모든 원소의 곱을 더한다.
//결국은 nCk라는 조합을 구하는 코드를 짜야한다.
import java.util.*;

class Solution {
    static Map<String,Integer> map;
    static Integer[] mapToArr;
    static int answer,size;//정답과 map의 길이
    static boolean[] v;
    
    public int solution(String[][] clothes) {

        map = new HashMap<>();
        //map에 정보 넣어주기
        putClothes(clothes);
        
        //정보 넣어줬으니까 입고 갈 수 있는 모든 경우의 수 카운트
        size = map.size();
        mapToArr = new Integer[size];
        
        //map에 있는 value를 계산하기 변하게 배열로 넘겨준다.
        mapToArrMed(); 
        
        // //조합으로 계산하기
        // answer = 1;
        // for(int k=1;k<=size;k++){
        //     v = new boolean[size];
        //     // combination(k,0,0);
        // }
        
        //여사건으로 계산하기
        answer = 1;
        for(int i=0;i<size;i++)
            answer *= (mapToArr[i]+1);
        
        return answer-1;
    }//main
    
    public static void combination(int k,int idx,int cnt){
        
        if(cnt==k && idx<=size){
            int ansTemp = 1;
            for(int i=0;i<size;i++)
                if(v[i]) ansTemp *= mapToArr[i];
            answer += ansTemp;
            return;
        }else if(idx==size) return;
        //기저 조건
        
        v[idx]=true;
        combination(k,idx+1,cnt+1);
        v[idx]=false;
        combination(k,idx+1,cnt);
        
        
    }//combination
    
    public static void mapToArrMed(){
        // Collection<Integer> values = map.values();
        mapToArr = map.values().toArray(new Integer[size]);
    }
    
    public static void putClothes(String[][] clothes){
        int total = clothes.length;
        for(int i=0;i<total;i++){
            String kind = clothes[i][1];
            int val = map.getOrDefault(kind,0);
            map.put(kind,val+1);
        }//for i
    }//putClothes
}