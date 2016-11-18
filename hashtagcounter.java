// Author: Devyash Sanghai
// Date: November 16th, 2016
// devyashsanghai@gmail.com

//Contains the Main Class for running the whole code

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class hashtagcounter {


    public static void main(String[] args){

        //Timer
      long startTime = System.currentTimeMillis();

        //path of the file as input
      String  pathtofile = args[0];

        //Hash Map for Storing the hashTag and the node
      HashMap<String,Node> hm = new HashMap();

        //Create an object of the fibonacci Heap
      FibonacciHeap fh = new FibonacciHeap();

        //Output File & writer pointer
      File file = new File("output_file.txt");
      BufferedWriter writer=null;

        // try IOException and other unchecked exception
      try {

        BufferedReader br = new BufferedReader(new FileReader(pathtofile));
        String s = br.readLine();

        Pattern p = Pattern.compile("([#])([a-z_]+)(\\s)(\\d+)");
        Pattern p1 = Pattern.compile("(\\d+)");
        writer = new BufferedWriter( new FileWriter(file));

        while (s != null) {

            Matcher m = p.matcher(s);
            Matcher m1 = p1.matcher(s);


            if (m.find()) {

                String hashTag = m.group(2);
                int key = Integer.parseInt(m.group(4));

                    //Check if it contains the key
                if ( !hm.containsKey(hashTag))
                {
                        //Create new node and insert in fibonacci heap and hash map
                    Node node = new Node(hashTag,key);
                    fh.insert(node);
                    hm.put(hashTag,node);


                }
                else
                {

                        //if already in hashmap then call increase key in fibonacci heap
                    int increaseKey = hm.get(hashTag).key + key;
                    fh.increaseKey(hm.get(hashTag),increaseKey);
                }
            } else if (m1.find()) {

                    //Number of Nodes to be removed
                int removeNumber = Integer.parseInt(m1.group(1));

                    //Removed Nodes
                ArrayList<Node> removedNodes = new ArrayList<Node>(removeNumber);

                for ( int i=0;i<removeNumber;i++)
                {

                        //Removed Node
                    Node node = fh.removeMax();

                        //remove from hashmap
                    hm.remove(node.getHashTag());

                        //Create new node for insertion
                    Node newNode= new Node(node.getHashTag(),node.key);

                        //add the new node for insertion into removed nodes list
                    removedNodes.add(newNode);

                        //Add the , until the last hashTag
                    if ( i <removeNumber-1) {
                        writer.write(node.getHashTag() + ",");

                    }

                    else {

                        writer.write(node.getHashTag());


                    }

                }

                    //insertion step
                for ( Node iterate : removedNodes)
                {

                    fh.insert(iterate);
                    hm.put(iterate.getHashTag(),iterate);


                }

                    //go to new line in writer pointer
                writer.newLine();
            }

                //Go to Next Line
            s = br.readLine();
        }
    }

    catch(Exception e){
        System.out.println(e);
    }
        //Close the writer
    finally {
        if ( writer != null ) {
            try {
                writer.close();
            } catch (IOException ioe2) {

            }
        }
    }

        //Print the time required
    long endTime   = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.println(" Total Time in milli seconds: "+ totalTime);


    }


}