MST - Minimum Spanning Tree
is a subgraph, sum of which edges has the minimum weight
(subgraph should be acyclic and including all the vertices(spanning))

notes:
- graph should be connected i.e. all nodes are connected

Разрез графа - A cut of a graph is a partition of its vertices into two disjoint sets

Kruskal's algorithm:
- time nlogn+n=nlogn, space n, where n is E - number of edges
- sort edges by weight ASC (can use PQ)
- create subgraph by adding edges one by one
- if edges creates a cycle, dont add it
- we can exit faster when we got all V verteces
- 
