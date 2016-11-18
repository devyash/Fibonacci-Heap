// Author: Devyash Sanghai
// Date: November 16th, 2016
// devyashsanghai@gmail.com


//Creates a new heap of fibonacci heap


import java.util.*;


public class FibonacciHeap
{

    private Node maxNode;
    private int numberOfNodes;

    //Insert a new node in the heap
    public void insert(Node node)
    {


        //check if max node is not null
        if (maxNode != null) {

            //add to the right of max node
            node.left = maxNode;
            node.right = maxNode.right;
            maxNode.right = node;

            //check if node right is not null
            if ( node.right!=null) {                               
                node.right.left = node;
            }
            if ( node.right==null)
            {
                node.right = maxNode;
                maxNode.left = node;
            }
            if (node.key > maxNode.key) {
                maxNode = node;
            }
        } else {
            maxNode = node;

        }

        numberOfNodes++;
    }


    //performs cut operation. Cuts x from y
    public void cut(Node x, Node y)
    {
       
    }

    //Performs cascading cut on the given node as given in Cormen 
    public void cascadingCut(Node y)
    {
        Node x = y.parent;

        //if there is a parent
        if (x != null) {
            // if y is unmarked, set it marked
            if (!y.childCut) {
                y.childCut = true;
            } else {
                // it's marked, cut it from parent
                cut(y, x);

                // cut its parent as well
                cascadingCut(x);
            }
        }
    }

    //Increase the value of key for the given node in heap
    public void increaseKey(Node x, int k)
    {
        if (k < x.key) {
        }

        x.key = k;

        Node y = x.parent;

        if ((y != null) && (x.key > y.key)) {
            cut(x, y);
            cascadingCut(y);
        }

        if (x.key > maxNode.key) {
            maxNode = x;
        }
    }

    //Removes the maximum from the heap
    public Node removeMax()
    {
        Node z = maxNode;
        if (z != null) {
            int numberofChildren = z.degree;
            Node x = z.child;
            Node tempRight;

            //while  there are childred of max
            while (numberofChildren > 0) {
                tempRight = x.right;

                // remove x from child list
                x.left.right = x.right;
                x.right.left = x.left;

                // add x to root list of heap
                x.left = maxNode;
                x.right = maxNode.right;
                maxNode.right = x;
                x.right.left = x;

                // set parent to null
                x.parent = null;
                x = tempRight;
                //decrease number of children of max
                numberofChildren--;

            }


            // remove z from root list of heap
            z.left.right = z.right;
            z.right.left = z.left;

            if (z == z.right) {
                maxNode = null;

            } else {
               maxNode = z.right;
               degreewiseMerge();
           }
           numberOfNodes--;
           return z;
       }
        return null;
    }

    //performs degree wise merge(if 2 degrees are same then it merges it)
    public void degreewiseMerge()
    {
        //chosen at random, read on internet that 45 is most optimised,
        // else can be calculated using the formulae given in cormen
        int sizeofDegreeTable =45;


        List<Node> degreeTable =
        new ArrayList<Node>(sizeofDegreeTable);

        // Initialize degree table
        for (int i = 0; i < sizeofDegreeTable; i++) {
            degreeTable.add(null);
        }
                       


        // Find the number of root nodes.
        int numRoots = 0;
        Node x = maxNode;


        if (x != null) {
            numRoots++;
            x = x.right;                     

            while (x != maxNode) {
                numRoots++;
                x = x.right;
            }
        }

        // For each node in root list 
        while (numRoots > 0) {

            int d = x.degree;
            Node next = x.right;

            // check if the degree is there in degree table, if not add,if yes then combine and merge
            for (;;) {
                Node y = degreeTable.get(d);
                if (y == null) {
                    break;
                }

                //Check whos key value is greater
                if (x.key < y.key) {
                    Node temp = y;
                    y = x;
                    x = temp;
                }

                //make y the child of x as x key value is greater
                makeChild(y, x);

                //setthe degree to null as x and y are combined now
                degreeTable.set(d, null);
                d++;
            }

            //store the new x(x+y) in the respective degree table postion
            degreeTable.set(d, x);

            // Move forward through list.
            x = next;
            numRoots--;
        }



        
        //Deleting the max node
        maxNode = null;

        // combine entries of the degree table
        for (int i = 0; i < sizeofDegreeTable; i++) {
            Node y = degreeTable.get(i);
            if (y == null) {
                continue;
            }

            //till max node is not null
            if (maxNode != null) {

                // First remove node from root list.
                y.left.right = y.right;
                y.right.left = y.left;

                // Now add to root list, again.
                y.left = maxNode;
                y.right = maxNode.right;
                maxNode.right = y;
                y.right.left = y;

                // Check if this is a new maximum
                if (y.key > maxNode.key) {
                    maxNode = y;
                }
            } else {
                maxNode = y;
            }
        }
    }

    //Makes y the child of node x
    public void makeChild(Node y, Node x)
    {
        // remove y from root list of heap
        y.left.right = y.right;
        y.right.left = y.left;

        // make y a child of x
        y.parent = x;

        if (x.child == null) {
            x.child = y;
            y.right = y;
            y.left = y;
        } else {
            y.left = x.child;
            y.right = x.child.right;
            x.child.right = y;
            y.right.left = y;
        }

        // increase degree of x by 1
        x.degree++;

        // make childCut of y as false
        y.childCut = false;
    }

}