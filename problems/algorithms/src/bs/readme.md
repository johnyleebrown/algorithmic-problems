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

### problems
https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/discuss/446376/javacpython-bianry-search/401806
https://leetcode.com/problems/divide-chocolate/discuss/408503/Python-Binary-Search
https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/discuss/256729/javacpython-binary-search/351188?page=3
https://leetcode.com/problems/koko-eating-bananas/discuss/152324/C++JavaPython-Binary-Search
https://leetcode.com/problems/minimize-max-distance-to-gas-station/discuss/113633/Easy-and-Concise-Solution-using-Binary-Search-C++JavaPython
https://leetcode.com/problems/split-array-largest-sum/

### reference
https://brestprog.by/topics/binsearch/
https://leetcode.com/problems/sqrtx/discuss/480399/Binary-Search-Summary

