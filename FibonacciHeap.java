/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;
import java.util.List;

/**
 * Class for Fibonacci Heap
 * @author Sudarsan
 */
public class FibonacciHeap
{
    //Minimum value in the heap
    private Node minVal = null;

    //Size of the heap
    private int size = 0;
    
    /**
     * Function for finding the minimum value in the heap
     * @return Node with the minimum priority
     */
    //
    public Node minimum()
    {        
        return minVal;
    }
    
    /**
     * Function for checking whether the heap is empty
     * @return true if heap is empty
     */
    public boolean isEmpty()
    {
        return minVal == null;
    }

    //Function returning the size of the heap
    public int size()
    {
        return size;
    }
    
    /**
     * Function for inserting the node into the heap
     * @param value - Value for the Node
     * @param key - Priority for the Node
     * @return The inserted Node
     */
    public Node insertNode(Integer value, Integer key)
    {
        //Create a new node with the input value
        Node insertedNode = new Node(value, key);

        //Merge the node with the tree
        minVal = meldNodes(minVal, insertedNode);

        //Increase the size of the heap
        size = size+1;

        // Return the inserted node
        return insertedNode;
    }
    
    /**
     * Function for removing the node with minimum priority from the heap
     * @return the removed node
     */
    public Node removeMin()
    {
        //if the node is empty return null
        if (isEmpty())
            return null;
               
        --size;

        //Get the minimum value and store it in temporary variable as we are gonna delete this
        Node tempMin = minVal;

       //After removing the min, if there is no element in the list, set the min val to null
        if (minVal.next == minVal) 
        {
            minVal = null;
        }
        
        //Arbitrary Remove
        else
        { 
            minVal.previous.next = minVal.next;
            minVal.next.previous = minVal.previous;
            minVal = minVal.next; 
        }

        //Remove the parent pointer of min element children
        if (tempMin.child != null)
        {
            //Store the first child of min element
            Node visitedChild = tempMin.child;
            do
            {
                visitedChild.parent = null;
                visitedChild = visitedChild.next;
            } while (visitedChild != tempMin.child);
        }

        //Meld the children to the top circular list and update the min pointer
        minVal = meldNodes(minVal, tempMin.child);

        
        if (minVal == null)
            return tempMin;

        //List for maintaining tree for each degree
        List<Node> tree = new ArrayList<>();

        //List for the remaining nodes,which needs to be visited
        List<Node> remainingNodes = new ArrayList<>();

        //Add the nodes to the list
        for (Node n = minVal; remainingNodes.isEmpty() || remainingNodes.get(0) != n; n = n.next)
            remainingNodes.add(n);

        //Traverse the list and perform union operation
        for (Node m1: remainingNodes) {
            
            while (true)
            {
                
                while (m1.degree >= tree.size())
                    tree.add(null);

               
                if (tree.get(m1.degree) == null)
                {
                    tree.set(m1.degree, m1);
                    break;
                }

                
                Node m2 = tree.get(m1.degree);
                tree.set(m1.degree, null);

                //Find the min and max of two trees
                Node min;
                Node max;
                
                if(m2.key<m1.key)
                {
                    min = m2;
                }
                else
                {
                    min = m1;
                }
                
                 if(m2.key<m1.key)
                {
                    max = m1;
                }
                else
                {
                    max = m2;
                }
                

                
                
                max.next.previous = max.previous;
                max.previous.next = max.next;

                //Meld the max tree
                max.next = max.previous = max;
                min.child = meldNodes(min.child, max);
                
                
                max.parent = min;

                
                max.isChildCut = false;

                
                //Increase the degree of the minimum tree, as a new child has been added to it
                ++min.degree;

                m1 = min;
            }

                //update the minimum pointer
                if (m1.key <= minVal.key)
                    minVal = m1;
        }
        return tempMin;
    }
    
    /**
     * Function for removing the node from the heap
     * @param nr - The Node which needs to be removed
     */
    
    public void remove(Node nr)
    {
        //After removing the node, set its key value Integer minimum
        decreaseKeyActual(nr, Integer.MIN_VALUE);

        //And remove the node 
        removeMin();
    }
    
    /**
     * Function for checking whether the given priority is valid
     * and decreasing the key of the given node, if the 
     * @param n - The Node for which the key value needs to be decreased
     * @param newKey - The new priority value
     */
    public void decreaseKey(Node n, Integer newKey)
    {
        //if the new key is greater than the previous one, throw illegal argument exception
        if (newKey > n.key)
        {
            throw new IllegalArgumentException("New key exceeds the previous key value");
        }

        //Decrease the key if the new key is less than the previous one
        decreaseKeyActual(n, newKey);
    }
    
    /**
     * Function for decreasing the key of the given node without any check
     * @param node - The Node for which the key value needs to be decreased
     * @param key - The new priority value
     */
    private void decreaseKeyActual(Node node, Integer key)
    {
        //Set the node with the new key value
        node.key = key;

        //Check for performing the cascading cut
        if (node.parent != null && node.key <= node.parent.key)
            cascadeCut(node);

        //update the minVal
        if (node.key <= minVal.key)
            minVal = node;
    }
   
    /**
     * Function performing cascading cut for the given node
     * @param node - Node which needs to be cut from the parent
     */
    private void cascadeCut(Node node)
        {
            //Set the Node's isCut property to false
             node.isChildCut = false;

             //if the nodes parent is null return nothing
             if (node.parent == null) 
                 return;


             if (node.next != node)
             { 
                 node.next.previous = node.previous;
                 node.previous.next = node.next;
             }


             if (node.parent.child == node)
             {

                 if (node.next != node)
                 {
                     node.parent.child = node.next;
                 }

                 else {
                     node.parent.child = null;
                 }
             }

             //Decrease the degree of the parent node
             --node.parent.degree;



             node.previous = node.next = node;
             minVal = meldNodes(minVal, node);

             //Check if the parent nodes isChildCut property is true
             //if yes perform cascading cut recursively
             if (node.parent.isChildCut)
             {
                 cascadeCut(node.parent);
             }
             else
             {
                 node.parent.isChildCut = true;
             }


             node.parent = null;
        }
    
    /**
     * Function for melding the heaps
     * @param h1 - fibonacci heap1
     * @param h2 - fibonacci heap2
     * @return - The Melded heap
     */
    public static  FibonacciHeap meld(FibonacciHeap h1, FibonacciHeap h2)
    {
        //Heap for storing the melded heaps
        FibonacciHeap meldedHeap = new FibonacciHeap();

        meldedHeap.minVal = meldNodes(h1.minVal, h2.minVal);

        //Set the size of the melded heap
        meldedHeap.size = h1.size + h2.size;

        //Clear the old heaps
        h1.size = h2.size = 0;
        h1.minVal  = null;
        h2.minVal  = null;

        //return the melded heap
        return meldedHeap;
    }

    /**
     * Function for melding two nodes
     * @param n1 - Node1
     * @param n2 - Node2
     * @return - The Melded Node
     */
    public static  Node meldNodes(Node n1, Node n2)
    {
        
        if (n1 == null && n2 == null)
        { 
            return null;
        }
        else if (n1 != null && n2 == null)
        { 
            return n1;
        }
        else if (n1 == null && n2 != null)
        { 
            return n2;
        }
        else
        { 
            Node oneNext = n1.next; 
            n1.next = n2.next;
            n1.next.previous = n1;
            n2.next = oneNext;
            n2.next.previous = n2;
            
            if(n1.key < n2.key)
            {
                return n1;
            }
            else
            {
                return n2;
            }
        }
    }

    

    /**
     * Class for storing the Node Details
     */
    public class Node
    {
        //Declare the node properties
        private String value;
        private Integer key;
        
        private Node parent;
        private Node child;
        private Node previous;
        private Node next;
        
        private Integer degree =0;
        private boolean isChildCut = false;
        
        /**
         * Function for getting the value of the Node
         * @return The value for the node
         */
        public String getValue()
        {
            return value;
        }
        
        /**
         * Function for setting the value of the node
         * @param value The Value for the node
         */
        public void setValue(String value)
        {
            this.value = value;
        }
        
        /**
         * Function for getting the key of the Node
         * @return The key value of the node
         */
        public Integer getKey()
        {
            return key;
        }
        
        /**
         * Constructor for initializing the node with the key and value
         * @param val Value for the node
         * @param keyval Key for the node
         */
         private Node(String val, Integer keyval)
         {
            next = previous = this;
            value = val;
            key = keyval;
         }   
    }
    
}
