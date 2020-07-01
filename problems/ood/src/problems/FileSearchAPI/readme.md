https://leetcode.com/discuss/interview-experience/528371/Amazon-or-SDE2-or-Seattle-or-Mar-2020

As for what I would expect (not necessarily all of these):

Obviously coming straight to the right design (encapsulating the Filtering logic into its own interface etc...), with an explanation on why this approach is good. I'm obviously open to alternate approaches as long as they are as flexible and elegant.
Implement boolean logic: AND/OR/NOT, here I want to see if the candidate understands object composition
Support for symlinks. Rather than seeing the implementation (which I don't really care about) I want to understand the tradeoffs of adding yet another parameter to the find method VS other options (eg. Constructor). Keep adding params to a method is usually bad.
How to handle the case where the result is really big (millions of files), and you may not be able to put all of them into a List.

https://leetcode.com/discuss/interview-question/369272/Amazon-or-Onsite-or-Linux-Find-Command

https://leetcode.com/articles/design-in-memory-file-system/#

https://github.com/nikhilagrwl07/System-Design/tree/master/src/main/java/FileAndDirectorySystem