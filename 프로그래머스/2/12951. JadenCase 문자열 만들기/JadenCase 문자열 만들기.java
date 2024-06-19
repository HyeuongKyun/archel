class Solution {
    public String solution(String s) {
        String madeS = s + 'a';//마지막 공백은 split가 못 받아서
        // String ah = "  for the what 1what  a";
        // String[] ahArr = ah.split(" ");
        // System.out.println(" ");
        // for(int i=0; i<ahArr.length; i++){
        //     System.out.println(ahArr[i]);
        //     System.out.printf("%s%d\n","=======",i);
        // }
        return convention(madeS);
    }
    
//     public String convention(String s){
//         String[] arrStr = s.split(" ");
//         for(int i=0 ; i < arrStr.length;i++){
//             String newStr = "";
//             char firstChar = arrStr[i].charAt(0);
//             if(firstChar>=97 && firstChar<=122) newStr += (char)(firstChar - 32);
//             else newStr += firstChar;
            
//             for(int j=1;j<arrStr[i].length();j++)
//             {
//                 char middleChar = arrStr[i].charAt(j);
//                 if(middleChar>=65 && middleChar<=90) newStr += (char)(middleChar + 32);
//                 else newStr += middleChar;
//             }
//             arrStr[i] = newStr;
            
            
//         }
//         StringBuilder sb = new StringBuilder();
//         for(int i=0;i<arrStr.length;i++){
//             if(i==arrStr.length-1) sb.append(arrStr[i]);
//             else sb.append(arrStr[i]).append(" ");
//         }
//         return sb.toString();
//     }
    public String convention(String s){
        String[] arrStr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<arrStr.length; i++){
            String str = arrStr[i];
            String newStr = "";
            if(!str.trim().equals("")){
                str = str.toLowerCase();
                char charStr = str.charAt(0);
                if(charStr>=97 && charStr<=122){
                    newStr += (char) (charStr-32);
                    if(str.length()>1) newStr += str.substring(1);
                } else newStr += str;
            }
            sb.append(newStr).append(" ");
            // System.out.println(str);
        }
        String result = sb.toString();

        return result.substring(0,result.length()-2);
    }
}