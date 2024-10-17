#include<stdio.h>
void main()
{
int a[50],i,j,n,temp;
printf("\n\tBUBBLE SORT");
printf("\n\t___________\n\n");
printf("\n\tEnter the limit:");
scanf("%d",&n);
printf("\n\tEnter the elements:");
for(i=0;i<n;i++)
{
scanf("%d",&a[i]);
}
printf("\n\n\tThe elements are:");
for(i=0;i<n;i++)
{
printf("\t%d",a[i]);
}
for(i=0;i<n-1;i++)
{
for(j=0;j<n-1-i;j++)
{
if(a[j]>a[j+1])
{
temp=a[j];
a[j]=a[j+1];
a[j+1]=temp;
}
}
}
printf("\n\n\tThe sorted array is");
for(i=0;i<n;i++)
{
printf("\t%d",a[i]);
}
}