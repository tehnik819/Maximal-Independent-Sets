# Finding all maximal independent sets of graph

Implemented algorithm of finding all maximal independent sets in an undirected graph.

Feature is no need to memorize generated
sets in order to check on their maximality by
comparing with the previously formed sets. The idea is
consistent expansion of the current independent set.
Obviously, if we can not extend the current solution, then
founded maximal independent set. Take him and
continue the search process.

# Input:
 - Number of graph's vertexes
 - Adjacency matrix
 
# Output:
 - The list of all maximal independent sets of graph