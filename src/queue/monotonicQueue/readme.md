#### todo
https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
LC907. Sum of Subarray Minimums
LC891. Sum of Subsequence Widths
LC828. Unique Letter String
https://leetcode.com/problems/remove-duplicate-letters/
https://leetcode.com/problems/remove-k-digits/
https://leetcode.com/problems/Max-Chunks-To-Make-Sorted-II/description/
https://leetcode.com/problems/sum-of-subarray-minimums/

https://leetcode.com/problems/create-maximum-number/
https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/339959/One-Pass-O(N)-Time-and-Space
https://leetcode.com/problems/sum-of-subarray-minimums/discuss/170750/C++JavaPython-Stack-Solution
https://leetcode.com/problems/score-of-parentheses/
https://leetcode.com/problems/container-with-most-water/

#### Histogram explanation
I'll maintain a stack of blocks that are in the increasing order. Once I see that a block comes my way that is smaller than the latest block that I have seen so far, I know that the rectangle that can be formed by the last block can't be extended beyond that one anymore. So, I'll go and process the contents of the stack to find out what's the max area that can be obtained by the blocks that are a part of the stack so far.
Once I've processed those blocks, I know what's the max area rectangle that's supported by those blocks. And now I'll start working from the block that I have just seen by adding it to the stack and continue repeating the process.

