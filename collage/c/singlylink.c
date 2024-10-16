Single linked list
#include&lt;stdio.h&gt;
#include&lt;stdlib.h&gt;
struct node
{ int data;
struct node *link;
}*head=NULL,*temp,*newnode,*prev;
void insert();
void delete();
void insertatf();
void display();
void insertspecific();
void insertate();
void specificdelete();
void fdelete();
void edelete();
int n;
int main()
{ while(1)
{ printf(&quot;\nEnter the choice\n1.insertion\n2.deletion\n3.display\n4.exit\n&quot;);
scanf(&quot;%d&quot;,&amp;n);
switch(n)
{ case 1:insert();
break;
case 2:delete();
break;
case 3:display();
break;
case 4:return 0;
default: printf(&quot;Invalid choice&quot;); } }}
void insert()
{ newnode=(struct node*)malloc(sizeof(struct node));
printf(&quot;Enter the data: &quot;);
scanf(&quot;%d&quot;,&amp;newnode-&gt;data);
newnode-&gt;link=NULL;
while(1)
{ printf(&quot;\nWhere to insert node\n1.insert at front\n2.insert at end\n3.insert after specified node\n4.back to main
menu\n&quot;);
scanf(&quot;%d&quot;,&amp;n);
switch(n)
{ case 1:insertatf();
return;
case 2:insertate();
return;
case 3:insertspecific();
return;
case 4:return;
default: printf(&quot;Invalid choice&quot;); } }}
void insertatf()
{ if(head==NULL)
head=newnode;
else
{ newnode-&gt;link=head;
head=newnode; }}
void insertate()
{ if(head==NULL)
head=newnode;

else
{ temp=head;
while(temp-&gt;link!=NULL)
temp=temp-&gt;link;
temp-&gt;link=newnode;
printf(&quot;Node Inserted&quot;);}}
void insertspecific()
{ int k;
printf(&quot;Enter the data of specificied node: &quot;);
scanf(&quot;%d&quot;,&amp;k);
if(head==NULL)
printf(&quot;Insertion not possible&quot;);
else
{ temp=head;
while(temp-&gt;data!=k&amp;&amp;temp-&gt;link!=NULL)
temp=temp-&gt;link;
if(temp-&gt;data==k)
{ newnode-&gt;link=temp-&gt;link;
temp-&gt;link=newnode;
printf(&quot;Node Inserted&quot;); }
else
printf(&quot;Specified node not found&quot;);}}
void display()
{ if(head==NULL)
printf(&quot;List is Empty&quot;);
else
{ temp=head;
printf(&quot;List is : &quot;);
while(temp!=NULL)
{ printf(&quot;%d &quot;,temp-&gt;data);
temp=temp-&gt;link; } } }
void delete()
{ while(1)
{ printf(&quot;\n from where to delete?\n1.delete from front\n2.delete from end\n3.delete a specified node\n4.back to
main menu\n&quot;);
scanf(&quot;%d&quot;,&amp;n);
switch(n)
{ case 1:fdelete();
return;
case 2:edelete();
return;
case 3:specificdelete();
return;
case 4:return;
default: printf(&quot;Invalid choice&quot;); }}}
void fdelete()
{ if(head==NULL)
printf(&quot;list empty&quot;);
else
{ temp=head;
head=head-&gt;link;
free(temp);
printf(&quot;Node Deleted&quot;); }}
void edelete()
{ if(head==NULL)
printf(&quot;list empty&quot;);
else if(head-&gt;link==NULL)

{ temp=head;
head=NULL;
free(temp); }
else
{ prev=head;
temp=head-&gt;link;
while(temp-&gt; link!=NULL)
{ prev=temp;
temp=temp-&gt;link; }
prev-&gt;link=NULL;
free(temp); }
printf(&quot;Node Deleted&quot;); }
void specificdelete()
{ int k;
printf(&quot;Enter the data of specificied node: &quot;);
scanf(&quot;%d&quot;,&amp;k);
if(head==NULL)
printf(&quot;list empty&quot;);
else if(head-&gt;data==k)
{ temp=head;
head=head-&gt;link;
free(temp);
printf(&quot;Node Deleted&quot;); }
else
{ prev=head;
temp=head-&gt;link;
while(temp-&gt;data!=k&amp;&amp;temp-&gt;link!=NULL)
{ prev=temp;
temp=temp-&gt;link; }
if(temp-&gt;data!=k)
printf(&quot;Specified node not found&quot;);
else
{ prev-&gt;link=temp-&gt;link;
free(temp);
printf(&quot;Node Deleted&quot;); }}}