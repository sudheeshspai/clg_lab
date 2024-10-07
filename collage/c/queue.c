#include <stdio.h>
void main(){
int i,a[20],front=-1,rear=-1,size,t=0,o,item;
printf("enter size of queue:");
scanf("%d",&size);
while(t==0){
printf("\nMENU\n1.inject\n2.eject\n3.traverse\n4.EXIT\nenter option:");
scanf("%d",&o);
switch(o){
case 1:printf("\nenter item to be inserted:");
scanf("%d",&item);
if(rear==size-1){
printf("\nqueue full");}
else if(rear==-1){
front=0;
rear=0;
a[rear]=item;
}
else{
rear++;
a[rear]=item;}
break;
case 2: if(front==-1){

printf("\nempty queue");}
else if(front==rear){
item=a[front];
front=-1;
rear=-1;
printf("\n deleted item:%d",item);}
else{
printf("\n deleted item:%d",a[front]);
front++;
}
break;
case 3:if (front==-1){
printf("\nqueue empty");
}
else{
for(int i=front;i<=rear;i++){
printf("%d \t",a[i]);}}
break;
case 4: printf("\nexiting");
t=1;
break;
default: printf("\nwrong input");
}
}
}