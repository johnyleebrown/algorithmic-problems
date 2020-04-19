### theory
#### standard
```
lo = 0
hi = n - 1
while hi - lo >= 0
    int mid = lo + (hi - lo) / 2
    if (a[mid] == t)
        return mid
    if (a[mid] < t) 
        lo = mid + 1
    else
        hi = mid - 1
return lo // or -1
```
or
```
lo = 0
hi = n
while hi - lo > 0
    int mid = lo + (hi - lo) / 2
    if (a[mid] == t)
        return mid
    if (a[mid] < t) 
        lo = mid + 1
    else
        hi = mid
return lo // or -1
```
#### find first index of el <= x | > x
returning lo because we iterate until we have [lo,hi]
```
lo = -1
hi = n
while hi - lo > 1
    mid = lo + (hi - lo)/2
    if a[mid] <= x
        lo = mid
    else
        hi = mid
return lo
```

#### find left most index of el >= x | < x
returning hi because we iterate until we have [lo,hi]
```
lo = -1
hi = n
while hi - lo > 1
    mid = lo + (hi - lo)/2
    if a[mid] < x
        lo = mid
    else
        hi = mid
return hi
```

#### ternary search - find function min
```
lo = -1
hi = n
while hi - lo > 2 // or > eps
    m1 = lo + (hi - lo)/3
    m2 = lo + 2*(hi-lo)/3
    if f(m1) < f(m2)
        hi = m2
    else
        lo = m1
return (lo + hi)/2
```

#### ternary search - find function max
```
lo = -1
hi = n
while hi - lo > 2
    m1 = lo + (hi - lo)/3
    m2 = lo + 2*(hi-lo)/3
    if f(m1) > f(m2)
        hi = m2
    else
        lo = m1
return (lo + hi)/2
```

### problems


https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/discuss/256729/javacpython-binary-search/351188?page=3
https://leetcode.com/problems/koko-eating-bananas/discuss/152324/C++JavaPython-Binary-Search
https://leetcode.com/problems/minimize-max-distance-to-gas-station/discuss/113633/Easy-and-Concise-Solution-using-Binary-Search-C++JavaPython
https://leetcode.com/problems/split-array-largest-sum/

### reference
https://brestprog.by/topics/binsearch/
https://leetcode.com/problems/sqrtx/discuss/480399/Binary-Search-Summary

