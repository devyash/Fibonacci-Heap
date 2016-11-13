import java.io.*;
import java.nio.file.*;
import java.util.regex.*; 
import java.util.*;
class Main{

	public static void main(String[] args) {
		System.out.println("Main.java is runnning");
		FileReader();
	}

	public static void FileReader(){
		try	{
			//File file= new File("\\sampleInput.txt");
			BufferedReader reader = new BufferedReader(new FileReader("sampleInput.txt"));
			Pattern r1 = Pattern.compile("([#])([a-zA-Z]+)(\\s+)(\\d+)");
			Pattern r2 = Pattern.compile("(\\d+)");
			HashMap<String,Node> hm = new HashMap<String,Node>();
			String line=reader.readLine();
			FibonacciHeap fh= new FibonacciHeap();
			while(line!=null){
				Matcher m1 =r1.matcher(line);
				Matcher m2=r2.matcher(line);

				if (m1.find( )) {
					System.out.println(m1.group(4));
					System.out.println(m1.group(2));
					if( hm.containsKey(m1.group(2))==true){
						System.out.println("It contains Key");
						Node node=hm.get(m1.group(2));
						int newValue= Integer.parseInt(m1.group(4)) + node.key;
						System.out.println(newValue);
						fh.increaseKey(node,newValue);
					}
					else{
						
						System.out.println("It has no Key");
						Node node = new Node(Integer.parseInt(m1.group(4)));
						hm.put(m1.group(2), node);
						fh.insertNode(node);
					}
				}
				else if(m2.find( )){
					System.out.println("Found value: " + m2.group(0) );	
					ArrayList <Node> output= new ArrayList<>();
					for (int i=0;i<Integer.parseInt(m2.group(0));i++){

						Node value=fh.removeMax();
						System.out.println(value);
						output.add(value);
						String key=getKeyFromValue(hm, value).toString();
						System.out.println("--------------------------------");
						System.out.println(key);
					}
					for (Node i: output){
						fh.insertNode(i);
					}

				}
				line=reader.readLine();
			}


		}	
		catch(Exception e){
			System.out.println(e);
		}
	}


	public static Object getKeyFromValue(Map hm, Object value) {
		for (Object o : hm.keySet()) {
			if (hm.get(o)==value) {
				return o;
			}
		}
		return null;
	}
}