# Distributed-Algorithm-Coursework
Modeling of Tree Algorithm and Election Algorithm
1.	Building Tree Models

I build a HashMap<Integer, HashSet<Integer>> object to store the shape of different trees. The key in this HashMap is the ID of each node and the corresponding value is the neighbour list of this node. I totally have 3 types of trees: 

Balanced Binary Tree: Every level, except possibly the last, is completely filled, and all nodes are as far left as possible, which means in each layer, the nodes are always created from left to right. So actually, I am trying to build a ‘Complete Binary Tree’.

Unbalanced Binary Tree: All the descendants to the right of any node in this type of tree are not allowed to have any child while approximately all the descendants to the left have 2 children.   

Arbitrary Tree: This type of tree does not have any branches, which means that it only contains leaves and a root. Thus, all the leaves are the children of the root.

NB: In my codes, I do not define left or right children. So in each binary tree , I assume the child which is the first child of any node as left child and the later one as right child, while a root always has 2 children.

2.	Statistics

As requested, Tree Election Algorithm contains the diffusion part while Tree Wave Algorithm excludes this part. Although it is suggested to do 100*N iterations in each algorithm execution, I deem that this number is much more than I need and actually slowing down my program. Thus, I set the total rounds as 50* and number each node’s ID as 0, 1, ….N-1.
    
Balanced binary tree in Tree Wave Algorithm:
Number of nodes	10	100	1000
ID of the first deciding node	0	2	1
Number of rounds until the first diciding event	7	13	29
ID of the second diciding node	1	0	0
Number of rounds until the second deciding event	7	13	32
Total rounds	500	5000	50000

Unbalanced binary tree in Tree Wave Algorithm:
Number of nodes	10	100	1000
ID of the first deciding node	5	45	479
Number of rounds until the first diciding event	7	44	353
ID of the second diciding node	3	47	477
Number of rounds until the second deciding event	7	45	354
Total rounds	500	5000	50000

Arbitrary tree in Tree Wave Algorithm:
Number of nodes	10	100	1000
ID of the first deciding node	0	4	336
Number of rounds until the first diciding event	1	7	9
ID of the second diciding node	7	0	0
Number of rounds until the second deciding event	3	8	9
Total rounds	500	5000	50000

Balanced binary tree in Tree Election Algorithm:
Number of nodes	10	100	1000
ID of the initiator	1	19	174
ID of the first deciding node	1	1	6
Number of rounds until the first diciding event	3	21	57
ID of the last diciding node	5	59	862
Number of rounds until the last deciding event	7	36	71
Minimal ID (leader)	0	0	0
Total number of deciding nodes	10	100	1000
Total rounds	500	5000	50000

Unbalanced binary tree in Tree Election Algorithm:
Number of nodes	10	100	1000
ID of the initiator	3	8	764
ID of the first deciding node	7	89	791
Number of rounds until the first diciding event	7	66	823
ID of the last diciding node	2	2	2
Number of rounds until the last deciding event	13	144	1221
Minimal ID (leader)	0	0	0
Total number of deciding nodes	10	100	1000
Total rounds	500	5000	50000

Arbitrary Tree in Tree Election Algorithm:
Number of nodes	10	100	1000
ID of the initiator	1	44	375
ID of the first deciding node	0	0	0
Number of rounds until the first diciding event	4	10	13
ID of the last diciding node	5	47	835
Number of rounds until the last deciding event	6	14	24
Minimal ID (leader)	0	0	0
Total number of deciding nodes	10	100	1000
Total rounds	500	5000	50000




3.	Observation

According to the diagrams shown above, when the total number of nodes becomes 10 times larger
each time in both Tree Wave Algorithm and Tree Election Algorithm, the number of iterations that it takes for the first and last deciding nodes to show up stays stable under 20 in arbitrary trees while this number increases slowly in balanced binary trees. By contrast, this figure rises dramatically in unbalanced binary trees. 

As Tree Wave Algorithm ignores the diffusion part, it ends with 2 deciding nodes each time; while
all the nodes deicide and know who is the leader at last in Tree Election Algorithm with diffusion part. 

Therefore, I conlude that arbitrary tree is the most effective in these 3 types of trees and the efficiency of balanced binary tree is fairly close to it, while unbalanced binary tree is the most ineffective.
 






















