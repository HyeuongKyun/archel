#include <stdio.h>
#define LEN_INPUT 10

int main(void) {
    char s1[LEN_INPUT];
    scanf("%s", s1);
    // printf("%d\n", 'A');//65
    // printf("%d\n", 'a');//97
    
    for(int i=0;i<strnlen(s1);i++){
        if(s1[i] >= 'a') printf("%c", (char) (s1[i]-32)) ;
        else printf("%c", (char) (s1[i]+32));
    }
    return 0;
}
