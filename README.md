# Fibonacci-Heap
##Implementation of Fibonacci Heap in JAVA

The goal of this project is to implement a system to find the n most popular hashtags appeared on social media such as Facebook or Twitter. For the scope of this project hashtags are taken as an input file. Basic idea for the implementation is to use a max priority structure to find out the most popular hashtags.

##The project uses the following data structure. 
-  Max Fibonacci heap: Use to keep track of the frequencies of Hashtags. 
-  Hash table(Hash Map in java) : Key for the hash table is hashtag and value is pointer to the corresponding node in the Fibonacci heap. 

 The project is written in JAVA. I have implemented Fibonacci Heap in java and stored the address of all the nodes in a Hash Map (Built in Data Structure). I have written the project using JAVA without any external in build data structure.  Max Fibonacci heap is required because it has better theoretical bounds for increase key operation.
 
A Fibonacci heap is a data structure for priority queue operations, consisting of a collection of heap-ordered trees. It has a better amortized running time than many other priority queue data structures including the binary heap and binomial heap. Michael L. Fredman and Robert E. Tarjan developed Fibonacci heaps in 1984 and published them in a scientific journal in 1987. They named Fibonacci heaps after the Fibonacci numbers, which are used in their running time analysis.[1]


##Fibonacci Max Heap
###Amortised Complexity
- Space	O(1)
- Search	O(1)
- Insert	O(log n)
- Delete	O(1)
- Find Max	O(1)
- Delete Max	O(log n)
- Increase Key	O(1)
- Merge	O(1)


##COMPILING & RUNNING INSTRUCTIONS


The project has been compiled and tested on thunder.cise.ufl.edu and java compiler on local machine.
To execute the program,
You can remotely access the server using ssh username@thunder.cise.ufl.edu
For running the Hash Tag Counter
-	Extract the contents of the zip file
-	Type ‘make’ without the quotes.
-	Type ‘java hashtagcounter ‘file path/input_file_name.txt’ ’ without the quotes and add the file name and pathI’ve included the file sampleInput.txt . 
