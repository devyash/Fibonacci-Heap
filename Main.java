import java.io.*;
import java.nio.file.*;
import java.util.regex.*; 
import java.util.*;
class Main{

	public static void main(String[] args) {
		//System.out.println("Main.java is runnning");
		FileReader();
	}

	public static void FileReader(){
		try	{
			//File file= new File("\\sampleInput.txt");
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			Pattern r1 = Pattern.compile("([#])([a-zA-Z]+)(\\s+)(\\d+)");
			Pattern r2 = Pattern.compile("(\\d+)");
			HashMap<String,Entry> hm = new HashMap<String,Entry>();
			String line=reader.readLine();
			FibonacciHeap fh= new FibonacciHeap();
			while(line!=null){
				Matcher m1 =r1.matcher(line);
				Matcher m2=r2.matcher(line);

				if (m1.find( )) {
					// System.out.println(m1.group(4));
					if( hm.containsKey(m1.group(2))==true){
						// System.out.println("It contains Key");
						Entry node=hm.get(m1.group(2));
						int newValue= Integer.parseInt(m1.group(4)) + node.mPriority;
						System.out.println(newValue);
						fh.increaseKey(node,newValue);
					}
					else{
						System.out.println(m1.group(2));
						// System.out.println("It has no Key");
						Entry node = new Entry(Integer.parseInt(m1.group(4)));
						fh.enqueue(node);
						hm.put(m1.group(2), node);
					}
				}
				else if(m2.find( )){
					System.out.println("Found value: " + m2.group(0) );	
					System.out.println("--------------------------------");
					ArrayList <Entry> output= new ArrayList<>();
					for (int i=0;i<Integer.parseInt(m2.group(0));i++){

						Entry value=fh.dequeueMax();
						//System.out.println(value);
						output.add(value);
						String key=getKeyFromValue(hm, value).toString();
						System.out.println(key);
					}
					for (Entry i: output){
						fh.enqueue(i);
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
			if (hm.get(o).equals(value)) {
				return o;
			}
		}
		return null;
	}
}