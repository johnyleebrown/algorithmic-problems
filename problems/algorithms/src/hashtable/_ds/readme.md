### reference
https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/LinearProbingHashST.java
https://codeforces.com/blog/entry/60442?locale=ru
https://www.youtube.com/watch?v=KqqOXndnvic
https://www.youtube.com/watch?v=rLMikQTOVnI
https://www.geeksforgeeks.org/top-20-hashing-technique-based-interview-questions/?ref=rp
https://www.youtube.com/watch?v=rA1ZevamGDc
https://habr.com/en/post/142589/
https://codeforces.com/blog/entry/17507
https://codeforces.com/blog/entry/60445
https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D1%81%D0%BB%D0%B5%D0%B4%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D0%BE%D1%81%D1%82%D1%8C_%D0%9C%D0%BE%D1%80%D1%81%D0%B0_%E2%80%94_%D0%A2%D1%83%D1%8D
https://e-maxx.ru/algo/string_hashes

### theory
#### Cuckoo hashing - double hashing

#### DJB Hash Function
Magic Constant 5381:
  1. odd number
  2. prime number
  3. deficient number
  4. 001/010/100/000/101 b
  
DJBX33A (Daniel J. Bernstein, Times 33 with Addition)

This is Daniel J. Bernstein's popular `times 33' hash function as
posted by him years ago on comp.lang.c. It basically uses a function
like ``hash(i) = hash(i-1) * 33 + str[i]''. This is one of the best
known hash functions for strings. Because it is both computed very
fast and distributes very well.

The magic of number 33, i.e. why it works better than many other
constants, prime or not, has never been adequately explained by
anyone. So I try an explanation: if one experimentally tests all
multipliers between 1 and 256 (as RSE did now) one detects that even
numbers are not useable at all. The remaining 128 odd numbers
(except for the number 1) work more or less all equally well. They
all distribute in an acceptable way and this way fill a hash table
with an average percent of approx. 86%.

If one compares the Chi^2 values of the variants, the number 33 not
even has the best value. But the number 33 and a few other equally
good numbers like 17, 31, 63, 127 and 129 have nevertheless a great
advantage to the remaining numbers in the large set of possible
multipliers: their multiply operation can be replaced by a faster
operation based on just one shift plus either a single addition
or subtraction operation. And because a hash function has to both
distribute good _and_ has to be very fast to compute, those few
numbers should be preferred and seems to be the reason why Daniel J.
Bernstein also preferred it.


-- Ralf S. Engelschall <rse@engelschall.com>