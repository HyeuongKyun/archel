import java.util.*;

class Solution {
    static int oriTime, oriFee,extTime, extFee;//기본시간,요금 , 추가시간,요금
    static int recN;//records의 길이
    static Map<String,String> mapStr;//IN하는 시간을 넣고 만약에 안에 값이 이미 존재하면 안에 들어있던 값과 시간을 구해서 mapTime에 넣어준다.
    static Map<String,Integer> mapTime;
    static class NumTime implements Comparable<NumTime>{
        int totalTime;
        String carNum;
        NumTime(int totalTime,String carNum){
            this.totalTime = totalTime;
            this.carNum = carNum;
        }//constructor;
        @Override
        public int compareTo(NumTime o){
            return Integer.parseInt(this.carNum)-Integer.parseInt(o.carNum);
        }
    }//numTime
    public int[] solution(int[] fees, String[] records) {
        oriTime= fees[0];
        oriFee = fees[1];
        extTime= fees[2];
        extFee = fees[3];
        recN=records.length;
        mapStr = new HashMap<>();
        mapTime = new HashMap<>();
        for(int i=0;i<recN;i++){
            String[] tempArr = records[i].split(" ");
            // for(int n=0;n<3;n++) System.out.println(tempArr[n]);
            // System.out.println(tempArr[1]);
            String temTime = mapStr.getOrDefault(tempArr[1],"??");
            // System.out.println(temTime);
            if(!temTime.equals("??")){//OUT
                // System.out.println("correct1");
                calAndUpdate(tempArr,temTime);
            } else {//IN
                // System.out.println(tempArr[1]);
                mapStr.put(tempArr[1],tempArr[0]);
            }//if else
        }//for i
        
        //이제 들어왔다가 나간 차량에 대해서는 시간 정산이 끝났다.
        //이제부터 해야할 것은 아직 안나간 차량와 함께 주차비 정산 그 후 차량번호가 작은 순서대로 청구비 return
        for(String key : mapStr.keySet()){
            String inTime = mapStr.get(key);
            String[] inArr = inTime.split(":");
            
            int timeDiff = (23-Integer.parseInt(inArr[0]))*60 + (59-Integer.parseInt(inArr[1]));
            int total = mapTime.getOrDefault(key,0);
            mapTime.put(key,total+timeDiff);
        }
        
        //안나간 차량에 대해서 까지 다 시간 정산을 끝냈으니까 차량 번호 오름차순으로 가격 넣기.
        List<NumTime> list = new ArrayList<>();
        for(String key : mapTime.keySet()){
            int total = mapTime.get(key);
            NumTime nowNT = new NumTime(total,key);
            list.add(nowNT);
        }
        Collections.sort(list);
        
        
        int[] answer = new int[list.size()];
        // int[] answer = new int[2];
        for(int i=0;i<list.size();i++){
            int time = list.get(i).totalTime;
            int result = 0;
            if(time<=oriTime) result += oriFee;
            else {
                int qoute = (time-oriTime)/extTime;
                int remind = (time-oriTime)%extTime;
                if(remind==0) result += (oriFee + ((time-oriTime)/extTime)*extFee);
                else result += (oriFee + (((time-oriTime)/extTime)+1)*extFee);
            }
            answer[i] = result;
        }
        return answer;
    }//solution
    
    private static void calAndUpdate(String[] tempArr , String inTime){
        String outTime = tempArr[0];
        String[] outArr = outTime.split(":");
        String[] inArr = inTime.split(":");
        int timeDiff = (Integer.parseInt(outArr[0])-Integer.parseInt(inArr[0]))*60 + Integer.parseInt(outArr[1])-Integer.parseInt(inArr[1]);
        // System.out.println(timeDiff);//아직은 넣은 데이터가 없기 때문에 동작안함
        int total = mapTime.getOrDefault(tempArr[1],0);
        mapTime.put(tempArr[1],total+timeDiff);
        mapStr.remove(tempArr[1]); //지워 놔야 새로 들어갈 때도 계산을 한다.
    }//calAndUpdate
}