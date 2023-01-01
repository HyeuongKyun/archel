//풀이 방법 : 하나의 문서를 검사하는 최대 횟수 100+99+...+1;
//다음 문서 99+98+...+1; 총 O((1/4*N^4)) => 1250만 가능
import java.util.*;

class Solution {
    static class Node{
        int idx , priority;
        Node(int idx,int priority){
            this.idx = idx;
            this.priority = priority;
        }//construct
        @Override
        public String toString(){
            return "idx :" + idx + " priority :" + priority; 
        }
    }//Node
    static List<Node> list;
    public int solution(int[] priorities, int location) {
        list = new ArrayList<>();
        for(int i=0;i<priorities.length;i++)
            list.add(new Node(i,priorities[i]));    
        int num=1;
        
        while(!list.isEmpty()){
            Node tempNode = list.get(0);
    
            
            boolean check = false;
            for(int i=1;i<list.size();i++){
                if(tempNode.priority<list.get(i).priority) {
                    list.remove(0);
                    list.add(tempNode);
                    check = true;
                    break;
                }//if
            }//for i 
            if(!check) {
                if(tempNode.idx==location) return num;
                list.remove(0);
                num++;
            }
        }//while
        return -1;
    }
}