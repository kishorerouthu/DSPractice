Binary Trees
*************
*************
In Binary tree each of the node can have at most two children

                    Level   MaxNodes
        a      --->   1       1
      /  \
     b    c    --->   2       2
    / \   / \
   d   e  f  g --->   3       4

Properties of Binary Tree
**************************
1. Maximum number of nodes at level 'i' of a binary tree in 2^(i-1)

   Example :
   Level 1   =  2^(1-1)   =  1
   Level 2   =  2^(2-1)   =  2
   Level 3   =  2^(3-1)   =  4

2. Maximum number of nodes in a binary tree of height h is 2^h - 1
   Here the height of the tree is maximum number of nodes on root to leaf path.
   Height of a tree with single node is considered as 1

   Maximum of number of nodes in terms of nodes at each level leads to
   1 + 2 + 4 + 8 + ---- + 2 ^ (h-1)
   This is the geometric series with h terms and sum of this series is 2^h - 1

   Example :
   Height 1   =  2^1-1   =  1
   Height 2   =  2^2-1   =  3
   Height 3   =  2^3-1   =  7

3. In a binary tree with N nodes, minimum possible height or minimum possible levels is log(n+1) base 2
   We can derive this from 2nd property

   2^h - 1 = n
   2^h = n + 1
   log 2^h = log(n+1)
   h = log(n+1) base 2

4. A binary tree with L leaves has at least [logL base 2] + 1
   We can derive this from 1st property
   Let N be the Maximum nodes at level L i.e  N = 2^(L-1)

   N = 2^(L-1)
   logN = log 2^(L-1)
   logN base 2 = L-1
   L = [logN base 2] + 1

 5. In Binary tree, number of leaf nodes is always one more than non-leaf nodes
    L = T + 1
    Where L = Number of leaf nodes
          T = Number of internal nodes with trow children (non-leaf)