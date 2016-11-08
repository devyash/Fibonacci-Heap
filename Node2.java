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