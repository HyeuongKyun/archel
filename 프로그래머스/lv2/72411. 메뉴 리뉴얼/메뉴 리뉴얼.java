//풀이 : 10C2+10C3+...+10C10 = 2^10-1 = 약 1000
//이러한 주문이 최대 20개 1,000×20=20,000가지 경우를 모두 골라서 확인한다.
//그런데 요기서 모든 경우를 확인할 필요 없이 course 길이 만큼만 하면되니 뽑는 경우의 수가 최대 1,000×course.length만큼으로 줄어든다.
//확인하는 방법은 k개만큼 뽑고 뽑힌 char들 string으로 한 map을 이용한다 그리고니서 map에 있는 모든 키를 서칭해서 같이 2이상인 것들을 list에 넣어주고 정렬한다.

import java.util.*;

class Solution {
    static class Course{
        Course(String courseMenu, int cnt){
            this.courseMenu = courseMenu;
            this.cnt = cnt;
        }
        String courseMenu;//코스를 string으로 나타낸 것
        int cnt;//나온 횟수를 기록할 변수
        
        // @Overide
        // public static int compare(Course o){
        //     return o.
        // }
    }
    static Map<String, Integer> map;
    public String[] solution(String[] orders, int[] course) {
        map = new HashMap<>();
        for(int i=0; i<course.length; i++){
            for(int j=0; j<orders.length;j++){
                int[] selArr = new int[course[i]];
                //인자: 인덱스와 뽑은 위치와 뽑은 기록배열 어디까지 뽑을지
                String orderToSort = orders[j];
                char[] charArr = orderToSort.toCharArray();
                Arrays.sort(charArr);
                String order = "";
                for(int l=0; l<charArr.length; l++) order += charArr[l];
                combination(0, 0, selArr, order, course[i]);
            }//for j
        }//for i
        
        //모든 경우를 돌았으니 map에 있는 모든 값을 list에 넣고 정렬 후 반환하면된다.
        Map<Integer, List<Course>> mapList = new HashMap<>();
        map.forEach((key,value) -> {
            // System.out.printf("통과 전: %s %d \n",key,value);
            // System.out.printf("key: %s, %d \n",key, key.length());
            // int idx = 0;
            int idx = key.length();
            
            List<Course> tempList = mapList.getOrDefault(idx, new ArrayList<>());
            
            //메뉴가 2개 이상인게 처음 들어왔을 때
            if(tempList.size()==0){
                if(value>1) {
                    tempList.add(new Course(key, value));
                    mapList.put(idx,tempList);
                }
            //기존의 k개의 코스가 있는데 어떤 것이 살아남을지 정할 코드
            } else {
                if(value>1){
                    if(tempList.get(0).cnt < value){
                        tempList = new ArrayList<>();
                        tempList.add(new Course(key, value));
                    } else if(tempList.get(0).cnt == value) {
                        tempList.add(new Course(key, value));
                    }//if else if
                    mapList.put(idx,tempList);
                }//if
            }//if else
            
        });
        
        //맵에서 가져올 수 있는 정보를 2차원 리스트 배열로 가져왔으니 한 곳에 모아서 정렬
        List<String> list = new ArrayList<>();
        mapList.forEach((key, value) -> {
            // System.out.printf("통과 후: %s\n",key);
            // for(int i=0;i<value.size();i++) System.out.printf("통과 후: %s %d \n",value.get(i).courseMenu,value.get(i).cnt);
            
            
            // List<Course> tempList = mapList.get(key);
            for(int i=0;i<value.size();i++){
                list.add(value.get(i).courseMenu);
            }
        });
        
        Collections.sort(list);
        
        
        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++) answer[i] = list.get(i);
        // Arrays.toSort(answer);
        
        return answer;
    }//main
    
    public static void combination(int idx, int sel, int[] selArr, String order, int cnt){
        if(idx == cnt){
            String tempStr = "";
            //오름차순으로 정렬되어 있어서 바로 해도된다.
            for(int i=0;i<cnt;i++){
                tempStr += order.charAt(selArr[i]);
            }
            
            int tempInt = map.getOrDefault(tempStr,0);
            map.put(tempStr, tempInt+1);
            return;
        }//기저 조건
        
        //조합
        for(int i=sel; i<order.length(); i++){
            selArr[idx] = i;
            combination(idx+1, i+1, selArr, order, cnt);
        }
        
    }
}