#include <stdio.h>
int main(){
int q[30],n,f=-1,c,r=-1,t=0,item;
printf("enter no of elements:");
scanf("%d",&n);
while(t==0){
printf("\n\nMENU\n1.push\n2.pop\n3.inject\n4.eject\n5.display\n6.exit\nenter choice:");
scanf("%d",&c);
switch (c){
case 1:printf("enter element to be inserted:");
scanf("%d",&item);
if (f==0 && r==n-1){
printf("Deque is FUL");}
else if (r==-1){
f=0;
r=0;
q[f]=item;}
else if(f>0){
f=f-1;
q[f]=item;}
else{
for(int i=r ; i>=0;i--){
q[i+1]=q[i];
}
q[f]=item;
r=r+1;}

break;
case 2:if(f==-1){
printf("Deque is EMPTY");}
else if (f==r){
printf("Dequed item is %d",q[f]);
f=r=-1;}
else{
printf("Dequed item is %d",q[f]);
f=f+1;}
break;
case 3:printf("enter element to be inserted:");
scanf("%d",&item);
if(f==0 && r==n-1){
printf("Deque is FULL");}
else if(r==-1){
f=r=0;
q[r]=item;}
else if( r<n-1){
r=r+1;
q[r]=item;}
else{
for(int i=f;i<=r;i++){
q[i-1]=q[i];
q[r]=item;
f=f-1;}}
break;
case 4:if (f==-1){

printf("Deque is EMPTY");}
else if(f==r){
printf("Dequed item is %d",q[r]);
f=r=-1;}
else{
printf("Dequed item is %d",q[r]);
r=r-1;}
break;
case 5:if(f==-1){
printf("Deque is EMPTY");}
else{
for(int i=f;i<=r;i++){
printf("%d\t",q[i]);}}
break;
case 6:printf("exiting");
t=1;
break;
default:printf("wrong input");
break;}}}