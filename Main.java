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
			HashMap<String,node> hm = new HashMap<>();
			String line=reader.readLine();
    		while(line!=null){
				Matcher m1 =r1.matcher(line);
				Matcher m2=r2.matcher(line);

				if (m1.find( )) {
					Node A=new Node(Integer.parseInt(m1.group(4)));
					hm.put(m1.group(2),A);
		      	}
		      	else if(m2.find( )){
		      		System.out.println("Found value: " + m2.group(0) );
		      	}
		      	line=reader.readLine();
    		}
		}	
		catch(Exception e){
			System.out.println(e);
		}
		}
}