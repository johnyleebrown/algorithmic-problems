### Dynamic programming on full sum.
#### Desc
Let's say one of the dp parameters is the sum that we have to come up with/fill.
#### Problems
Coin Change, Knapsack, or segments problem(group into 2 groups so the sum of segments is equal)

### Dynamic programming in games.
#### Desc
Метод выигрышных позиций
Основные свойства позиций таковы:
1.) каждая позиция - либо выигрышная, либо проигрышная (промежуточных вариантов нет!);
2.) из выигрышной позиции можно пойти только на проигрышную;
3.) из любой проигрышной позиции можно пойти на выигрышную.
Стратегия одинакова: каждый раз ходить на выигрышную позицию. Тогда противник должен будет походить на проигрышную позицию (свойство 2), а мы опять сможем пойти на выигрышную (свойство 3).
Суть метода: делим всю доску (или все возможные ходы) на два вида полей — выигрываю­щие и проигрывающие (причем под это определение попадают все рассматриваемые клетки или ходы). После этого стратегия играющего заключается в том, чтобы делать свой ход на выигрывающие клетки (или делать выигрывающие ходы). Данный метод пригоден почти для всех игровых задач.
https://ru.coursera.org/lecture/matematicheskaya-teoria-igr/zadacha-optimal-nogho-upravlieniia-i-printsip-dinamichieskogho-ZxB0f

https://www.youtube.com/watch?v=o4eZBJspDzU&list=WL&index=52&t=0s

### Linear dynamic programming.
#### Desc
For all i calculating value using values from previous i-s.

### Multidimensional dynamic programming.
#### Desc
Similarly to linear dp we are calculating values in multi-d space: ans[i][j].

### Profile dynamic programming.
https://www.youtube.com/watch?v=IISXNH1ROds&list=WL&index=27&t=0s
https://www.youtube.com/watch?v=YBSt1jYwVfU&list=WL&index=38&t=0s
https://www.youtube.com/watch?v=7uh9ZeHYdfM&list=WL&index=39&t=0s

### Dynamic programming on substrings.
#### Desc
In substring dp for each l and r we calculate ans[l][r], 
where l is a left edge of a substring and r is a right edge.

### Dynamic programming on trees.
#### Desc

#### reference
http://codeforces.com/blog/entry/20935