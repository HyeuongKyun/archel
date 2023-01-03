//풀이 방법
//맵을 이용하여 장르별로 갯수를 카운트 해주고
//맵의 사이즈를 보고 몇 개의 장르가 있는지 파악
//노드를 고유번호와 재생횟수를 필드로 만들고 그걸 넣을 배열을 준비한다(만약 맵에 없는 키의 장르이면 근데 만약에 있으면 있는 배열에 추가만 해준다.이걸 해주는 방법은 다음과 같이 맵을 만든다.
//Map<String,List<IdxPlays>> 
//idxPlays는 고유번호와 재생횟수를 담고 있다
//)
//이러고 나서 맵의 길이를 보고 전체 장르 재생횟수를 기록할 노드를 만들어서 배열에 넣고 전체 재생 횟수 기준으로 오름차순 정렬은 한다.
//ex StrTotal(String str,int total) 그래서 최대인 str들에 앞에서 2번째 그 다음 2개 이런식으로 쭉쭉하면된다.
import java.util.*;

class Solution {
    static class IdxPlays implements Comparable<IdxPlays>{
        int idx,play;
        IdxPlays(int idx,int play){
            this.idx=idx;
            this.play=play;
        }
        
        @Override
        public int compareTo(IdxPlays o){
            //재생횟수기준 내림차순
            if(this.play>o.play) return -1;
            else if(this.play<o.play) return 1;
            else {
                //고유번호기 때문에 같을 수는 없다. 오름 차순
                if(this.idx>o.idx) return 1;
                else return -1;
            }
        }
    }//idxPlays
    static class StrTotal implements Comparable<StrTotal>{
        String str;
        int total;
        StrTotal(String str, int total){
            this.str = str;
            this.total = total;
        }
        
        @Override
        public int compareTo(StrTotal o){
            //문제에서 모든 장르는 재생된 횟수가 다르다했다. 오름차순 정렬
            if(this.total>o.total) return -1;
            else return 1;
        }
    }//strTotal
    static Map<String,PriorityQueue<IdxPlays>> map;//각 str에 해당하는 노래들을 담을 배열
    static List<StrTotal> listStrTotal;//str에 해당하는 모든 재생힛수 합들을 넣을 배열
    static List<List<IdxPlays>> listIdxPlays;//마지막에 각 노래들을 저장할 리스트
    static int N;//genres와 plays의 길이
    public int[] solution(String[] genres, int[] plays) {
        map = new HashMap<>();
        listStrTotal = new ArrayList<>();
        N = genres.length;
        // System.out.println(N);
        for(int n=0;n<N;n++){
            String genre = genres[n];
            int play = plays[n];
            if(map.containsKey(genre)){
                // System.out.println("처음아님");
                map.get(genre).add(new IdxPlays(n,play));
            } else {
                // System.out.println("처음");
                PriorityQueue<IdxPlays> pq = new PriorityQueue<>();
                pq.add(new IdxPlays(n,play));
                map.put(genre, pq);
            }//if,else
        }//for n 
        
        //map 채우기 끝
        //이제 map에 있는 key에 해당하는 pq들을 다 더해서 listStrTotal에 넣어주기.
        
        Set<String> keySet = map.keySet();
        
        for(String key : keySet){
            
            PriorityQueue<IdxPlays> pq = map.get(key);
            PriorityQueue<IdxPlays> newPQ = new PriorityQueue<>();
            List<IdxPlays> tempList = new ArrayList<>();
            int sum = 0;
            while(!pq.isEmpty()){
                IdxPlays temp = pq.poll();
                sum += temp.play;
                newPQ.add(temp);   
                // System.out.println(temp.idx);
            }//while
            listStrTotal.add(new StrTotal(key,sum));
            map.put(key,newPQ);
            //다시 넣어주는 이유는 key에 맞게끝 tempList를 채우기 위해서
        }
        
        //재생횟수가 많은 순으로 내림차순 정렬한다.
        Collections.sort(listStrTotal);
        
        listIdxPlays = new ArrayList<>();
        List<Integer> ansList = new ArrayList<>();
        for(int i=0;i<listStrTotal.size();i++){
            StrTotal temp = listStrTotal.get(i);
            // System.out.println(temp.str);
            PriorityQueue<IdxPlays> tempPQ = map.get(temp.str);
            if(tempPQ.size()<2)
                while(!tempPQ.isEmpty()){
                    int idx = tempPQ.poll().idx;
                    // System.out.println(idx);
                    ansList.add(idx);
                }
            else {
                IdxPlays temIdxPlay1 = tempPQ.poll();
                // System.out.println(temIdxPlay1.idx);
                ansList.add(temIdxPlay1.idx);
                IdxPlays temIdxPlay2 = tempPQ.poll();
                // System.out.println(temIdxPlay2.idx);
                ansList.add(temIdxPlay2.idx);
            }
        }
        
        //map에 있는 값들을 이용해서 listStrTotal를 채우고 map에 있는 값도 찾기 쉽게 listIdxPlays로 뺐다.
       
        
        
        int[] answer = new int[ansList.size()];
        
        for(int i=0;i<ansList.size();i++)
            answer[i] = ansList.get(i);
        
        return answer;
    }
}