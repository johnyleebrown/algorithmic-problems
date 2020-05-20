## Theory
### Path Compression
Path Compression is updating the parent of the node to parent's parent 
while doing the find operation, this way balancing the tree. It makes 
find op O(1) and makes avg distance to root 1.52 instead of 5.11 w/o PC.

### Percolation
We model the system as an n-by-n grid of sites. Each site is either blocked 
or open; open sites are initially empty. A full site is an open site that can 
be connected to an open site in the top row via a chain of neighboring (left, 
right, up, down) open sites. If there is a full site in the bottom row, then 
we say that the system percolates.