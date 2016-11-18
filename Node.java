// Author: Devyash Sanghai
// Date: November 16th, 2016
// devyashsanghai@gmail.com



//contains the Node class for creating the node object
public class Node
{
        Node left, right, child, parent;
        int degree = 0;       
        boolean childCut = false; 
        private String hashTag;
        int key;

        Node(String hashTag,int key)
        {
           this.left = this;
           this.right = this;
           this.parent = null;
           this.degree = 0;
           this.hashTag = hashTag;
           this.key = key;

        }

        public  String  getHashTag(){
            return this.hashTag;
        }

    }