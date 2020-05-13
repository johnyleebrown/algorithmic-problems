#### theory
- set bit
int set(int s, int i) {
    return s |= 1<<i;
}

- clear bit
int unset(int s, int i) {
    return s &= ~(1<<i);
}

- check if set
boolean check(int s, int i) {
    return ((s>>i)&1)==1;
}