    import java.util.ArrayList;
    import java.util.List;


    public class FibonacciHeap
    {
        //Maximum value in the heap
        private Node maxVal = null;

        //Size of the heap
        private int size = 0;
        

        public Node maximum()
        {      

            return maxVal;
        }
        
        public boolean isEmpty()
        {

            return maxVal == null;
        }

        //Function returning the size of the heap
        public int size()
        {

            return size;
        }

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

 
        public Node insertNode(Node insertedNode)
        {

            //Merge the node with the tree
            maxVal = meldNodes(maxVal, insertedNode);


            //Increase the size of the heap
            size = size+1;

            // Return the inserted node
            return insertedNode;
        }
        
         public static  FibonacciHeap meld(FibonacciHeap h1, FibonacciHeap h2)
        {
                //Heap for storing the melded heaps
                FibonacciHeap meldedHeap = new FibonacciHeap();

                meldedHeap.maxVal = meldNodes(h1.maxVal, h2.maxVal);

                //Set the size of the melded heap
                meldedHeap.size = h1.size + h2.size;

                //Clear the old heaps
                h1.size = h2.size = 0;
                h1.maxVal  = null;
                h2.maxVal  = null;

                //return the melded heap
                return meldedHeap;
        }

        public Node removeMax()
        {
            if (isEmpty())
                return null;

            --size;

            Node tempMax = maxVal;

            if (maxVal.next == maxVal) 
            {
                maxVal = null;
            }
            
            //Connecting the circular list
            else
            { 
                maxVal.previous.next = maxVal.next;
                maxVal.next.previous = maxVal.previous;
                maxVal = maxVal.next; 
            }

            //Remove the parent pointer of max element children
            if (tempMax.child != null)
            {
                //Store the first child of max element
                Node visitedChild = tempMax.child;
                do
                {
                    visitedChild.parent = null;
                    visitedChild = visitedChild.next;
                } while (visitedChild != tempMax.child);
            }

            //Meld the children to the top circular list and update the max pointer
            maxVal = meldNodes(maxVal, tempMax.child);

            
            if (maxVal == null)
                return tempMax;

            //List for maintaining tree for each degree
            List<Node> tree = new ArrayList<>();

            //List for the remaining nodes,which needs to be visited
            List<Node> remainingNodes = new ArrayList<>();

            //Add the nodes to the list
            for (Node n = maxVal; remainingNodes.isEmpty() || remainingNodes.get(0) != n; n = n.next)
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
                    if (m1.key >= maxVal.key)
                        maxVal = m1;
            }
            return tempMax;
        }


        public void increaseKey(Node node, Integer key)
        {
            //if the new key is less than the previous one, throw illegal argument exception
            if (key < node.key)
            {
                throw new IllegalArgumentException("New key is less than previous key value");
            }

            //Set the node with the new key value
            node.key = key;

            //Check for performing the cascading cut
            if (node.parent != null && node.key >= node.parent.key)
                cascadeCut(node);

            //update the maxVal
            if (node.key >= maxVal.key)
                maxVal = node;
        }
       

        private void cascadeCut(Node node)
            {
                //Set the Node's isCut property to false
                 node.isChildCut = false;

                 //if the nodes parent is null return nothing
                 if (node.parent == null) 
                     return;

                 //join the circular list of the child
                 if (node.next != node)
                 { 
                     node.next.previous = node.previous;
                     node.previous.next = node.next;
                 }

                 //set the child of parent as next or null if no next is there
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
                 maxVal = meldNodes(maxVal, node);

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
  
   
        
    }
