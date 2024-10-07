#include &lt;stdio.h&gt;
int s, f, r;
struct pq {
int item;
int priority;
} a[20];
void enqueue(int item, int priority) {
int loc, i;
if (f == 0 &amp;&amp; r == s - 1) {
printf(&quot;Queue is full\n&quot;);
} else if (f == -1 &amp;&amp; r == -1) {
f = r = 0;
a[r].item = item;
a[r].priority = priority;
} else {
if (r == s - 1) {
for (i = f; i &lt;= r; i++) {
a[i - 1] = a[i];
}
r--;
f--;
}
for (i = r; i &gt;= f; i--) {
if (a[i].priority &lt; priority) {
break;
}
}
loc = i + 1;
for (i = r; i &gt;= loc; i--) {
a[i + 1] = a[i];
}
a[loc].item = item;
a[loc].priority = priority;
r++;
}
}
void dequeue() {
if (f == -1 &amp;&amp; r == -1) {
printf(&quot;Queue is empty\n&quot;);
} else if (f == r) {
printf(&quot;Deleted item is %d\n&quot;, a[f].item);
f = r = -1;
} else {
printf(&quot;Deleted item is %d\n&quot;, a[f].item);
f++;
}
}
void display() {
if (f == -1 &amp;&amp; r == -1) {
printf(&quot;Queue is empty\n&quot;);
} else {
for (int i = f; i &lt;= r; i++) {
printf(&quot;Item: %d, Priority: %d\n&quot;, a[i].item, a[i].priority);
}
}
}
void main() {
int ch, item, priority;
printf(&quot;Enter the size of the queue\n&quot;);
scanf(&quot;%d&quot;, &amp;s);
f = r = -1;
do {
printf(&quot;1.Enqueue\n2.Dequeue\n3.Display\n4.Exit\n&quot;);
printf(&quot;Enter your choice\n&quot;);
scanf(&quot;%d&quot;, &amp;ch);

switch (ch) {
case 1:
printf(&quot;Enter the item and priority\n&quot;);
scanf(&quot;%d%d&quot;, &amp;item, &amp;priority);
enqueue(item, priority);
break;
case 2:
dequeue();
break;
case 3:
display();
break;
case 4:
break;
default:
printf(&quot;Invalid choice\n&quot;);
}
} while (ch != 4);}