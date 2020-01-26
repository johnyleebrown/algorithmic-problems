# Reservoir Sampling
This is a family of randomized algorithms.

## Algorithm R
```
int[] result, int[] source 
// fill result array of length k with k elements from source
for i in source:
	result[i] = source[i]


```
ReservoirSample(S[1..n], R[1..k]) // fill the reservoir array for i = 1 to k
R[i] := S[i]
      // replace elements with gradually decreasing probability
for i = k+1 to n
j := math.random(1, i) // important: inclusive range if j <= k
R[j] := S[i]
