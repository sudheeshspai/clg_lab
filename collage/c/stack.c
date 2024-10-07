#include <stdio.h>
int main(){
int i,a[20],top=-1,size,t=0,o,item;
printf("enter size of stack:");
scanf("%d",&size);
while(t==0){
printf("\nMENU\n1.PUSH\n2.POP\n3.DISPLAY\n4.EXIT\nenter option:");
scanf("%d",&o);
switch(o){
case 1:printf("\nenter item to be inserted:");
scanf("%d",&item);
if(top==size-1){
printf("\nstack overflow");}
else{
top=top+1;
a[top]=item;}
break;
case 2: if(top==-1){

printf("\nempty stack");}
else{
printf("\nitem to be popped:%d",a[top]);
top=top-1;}
break;
case 3:if (top==-1){

printf("\nstack empty");}
else{
printf("\nSTACK\n");
for(i=0;i<=top;i++){
printf("\n%d",a[i]);}}

break;
case 4: printf("\nexiting!");
t=1;
break;
default: printf("\nwrong input");
}
}
return 0;
}