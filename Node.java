public class Node
    {

        public int key;
        public Node parent;
        public Node child;
        public Node previous;
        public Node next; 
        public int degree =0;
        public boolean isChildCut = false;
        
        

        public Integer getKey()
        {
            
            return key;
        }

        public Node(Integer keyval)
        {
            next = previous = this;
            key = keyval;
        }


    }