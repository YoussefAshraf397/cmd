/////
import java.io.*;

import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

///////


public class Terminal {
	
	public static int Output_Port=0;
	public static String Outpot_File_Location="";
	
	//1>Overwrite
	//2>>Append
	
	
	///////////////////////////////////////////////////CLEAR/////////////////////////////////////////////////////////////////////
public void Clear() throws IOException {
		
		if (Output_Port!=0) {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(Outpot_File_Location, true));
			char c='\n';
			for(int i=0;i<100;i++)writer.newLine();
			writer.close();
		}
		else if (Output_Port==0) {
			for(int i=0;i<100;i++)System.out.println("\n");
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
public void pwd() throws IOException {
	if(Output_Port!=0)
	{
		if(Output_Port==1)
		{
			//Overwrite
			PrintWriter pw = new PrintWriter(Outpot_File_Location);
			pw.close();
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(Outpot_File_Location, true));
		
		writer.write(Parser.Defualt_Path);
		writer.close();
	}
	else if(Output_Port==0)
		System.out.println( Parser.Defualt_Path);	
}
	
	
    ///////////////////////////////////////////////////MORE/////////////////////////////////////////////////////////////////////
public void more(String path) throws IOException
{
	
	if (Output_Port ==1) {
		//Overwrite
		PrintWriter pw = new PrintWriter(Outpot_File_Location);
		pw.close();
	}
	
	
	
	Scanner bScanner=new Scanner(System.in);
	FileInputStream inputStream = new FileInputStream(path);
	byte n;
	char c;
	String string = "";
	while((n = (byte)inputStream.read()) != -1)
	{
		c = (char)n;
		string += c;
	}
	int spaces=0;
	
	for (int i = 0; i < string.length(); i++) {
		if (string.charAt(i)==' ') 
		{
			spaces++;
			if (spaces==10) {
				spaces=0;
				String x = bScanner.nextLine();		}}
		if (Output_Port!=0) {
			BufferedWriter writ = new BufferedWriter(new FileWriter(Outpot_File_Location, true));
			writ.write(string.charAt(i));
			writ.close();
		}
		else 
			System.out.print(string.charAt(i));
		
	} 
	
	
	
   //   System.out.println(string);
	
}
          ///////////////////////////////////////////////////COPPY/////////////////////////////////////////////////////////////////////
public void cp(String Source , String Destination)throws IOException
{
	
	
	for (int i = Source.length()-1; i > 0; i--) {
		if(Source.charAt(i)=='\\')
		{
			for (int j = i+1; j < Source.length(); j++) {
				Destination+=Source.charAt(j);
			}
			break;
		}
		
	}
	System.out.println(Source);
	System.out.println(Destination);	
	File source = new File(Source);
    File dest = new File(Destination);
	
	FileUtils.copyFile(source, dest);
}///////////////////////////////////////////////////////////

      ///////////////////////////////////////////////////MOVE/////////////////////////////////////////////////////////////////////
public void mv(String Source , String Destination)throws IOException
{
	for (int i = Source.length()-1; i > 0; i--) {
		if(Source.charAt(i)=='\\')
		{
			for (int j = i+1; j < Source.length(); j++) {
				Destination+=Source.charAt(j);
			}
			break;
		}
		
	}
	System.out.println(Source);
	System.out.println(Destination);	
	File source = new File(Source);
    File dest = new File(Destination);
	
	FileUtils.copyFile(source, dest);

	
	
}

      ///////////////////////////////////////////////////LIST/////////////////////////////////////////////////////////////////////
	public void ls(String Path) throws IOException {
		
		if(Output_Port==0)
		{
			System.out.println("\n");
			
			File folder = new File(Path);
	        
	        String[] files = folder.list();
	         
	        for (String file : files) 
	        {
	            System.out.println(file);
	        }
		}
		else if(Output_Port!=0)
		{
			if(Output_Port==1)
			{
				//Overwrite
				PrintWriter pw = new PrintWriter(Outpot_File_Location);
				pw.close();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(Outpot_File_Location, true));
			
			System.out.println("\n");
			
			File folder = new File(Path);
	        
	        String[] files = folder.list();
	         
	        for (String file : files) 
	        {
	        	 writer.write(file);
	             writer.newLine();
	        }
	        writer.close();
	        
		}
		
		
	}
	
     ///////////////////////////////////////////////////CHANGE DIRECTORY/////////////////////////////////////////////////////////////////////
	public String cd(String Path) {
		return Path;
	}
	
	public void rm(String Path) {
		File file = new File(Path);
		
		if(file.delete())
			System.out.println(file.getName() + " is deleted!");	
		else
			System.out.println("Delete operation is failed.");
		
			
	}
	
     ///////////////////////////////////////////////////MAKE DIRECTORY/////////////////////////////////////////////////////////////////////
	public void mkdir(String Path) {
		 
		    File dir = new File(Path); 
		    boolean successful = dir.mkdir();
		    
		    if (successful)
		    {
		      System.out.println("directory was created successfully");
		    }
		    else
		    {
		      System.out.println("failed trying to create the directory");
		    }
		
	}
	
     ///////////////////////////////////////////////////REMOVE DIRECTORY9/////////////////////////////////////////////////////////////////////
	public void rmdir(String Path) {
		 
	    File dir = new File(Path); 
	    boolean successful = dir.delete();
	    
	    if (successful)
	    {
	      System.out.println("Directory was deleted successfully");
	    }
	    else
	    {
	      System.out.println("Failed trying to delete the directory");
	    }
	
}
    ///////////////////////////////////////////////////DATE /////////////////////////////////////////////////////////////////////

	public void date() throws IOException
	{
		if (Output_Port ==1) {
			//Overwrite
			PrintWriter pw = new PrintWriter(Outpot_File_Location);
			pw.close();
		}
		    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now(); 
		   
		   if (Output_Port!=0) {
				if (Output_Port==1) {
					//Overwrite
					PrintWriter pw = new PrintWriter(Outpot_File_Location);
					pw.close();
				}
					BufferedWriter writer = new BufferedWriter(new FileWriter(Outpot_File_Location, true));
					writer.write(dtf.format(now));
					writer.close();
				}
		   else
			   System.out.println(dtf.format(now));
		   
		    
	}
	
    ///////////////////////////////////////////////////CONCATENATE/////////////////////////////////////////////////////////////////////
	public void cat(String path) throws IOException
	{
		if (Output_Port ==1) {
			//Overwrite
			PrintWriter pw = new PrintWriter(Outpot_File_Location);
			pw.close();
		}
		
		Scanner bScanner=new Scanner(System.in);
		String[]Store=path.split(" ");
				int spaces=0;
		for (int i = 0; i <path.length(); i++) 
		{
		
			if (path.charAt(i)==' ') 
			{
				spaces++;
				}
			
		} 
			
		
		FileInputStream inputStream = new FileInputStream(path);
		
		byte n;
		char c;
		String string = "";
		while((n = (byte)inputStream.read()) != -1)
		{
			c = (char)n;
			string += c;
		}
		
		if(Output_Port!=0)
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(Outpot_File_Location, true));
			writer.write(string);
			writer.close();
			Output_Port=12;
		}
		else
			System.out.println(string);
		
		string="";
		
		
	}
    ///////////////////////////////////////////////////HELP/////////////////////////////////////////////////////////////////////

	public void help() throws IOException
	{
		if (Output_Port!=0) {
			if (Output_Port==1) {
				//Overwrite
				PrintWriter pw = new PrintWriter(Outpot_File_Location);
				pw.close();
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(Outpot_File_Location, true));
				
				writer.write("args  : List all command  arguments"); writer.newLine();writer.write("date  :  Current date/time"); writer.newLine();writer.write("exit  : Stop all"); writer.newLine();writer.write("ls    : list all files in directory"); writer.newLine();writer.write("rm    : Remove the file"); writer.newLine();writer.write("pwd   : Print the work-in directory"); writer.newLine();writer.write("mkdir : mkdir creates a directory with each given name"); writer.newLine();writer.write("rmdir : rmdir removes each given empty directory"); writer.newLine();writer.write("cat   : Concatenate files and print on the standard output"); writer.newLine();writer.write("more  : Let us display and scroll down the output in one direction only"); writer.newLine();writer.write(">     : Redirect the output to be written to a file"); writer.newLine();writer.write(">>    : Redirect the output to be written to a file  create/append"); writer.newLine();writer.write("cd    : This command changes the current directory to another one"); writer.newLine();writer.write("clear : This command can be called to clear the current terminal screen");
				writer.close();
			}
			
			
		}
		System.out.println("args  : List all command  arguments\ndate  :  Current date/time\nexit  : Stop all\nls    : list all files in directory\nrm    : Remove the file\npwd   : Print the work-in directory\nmkdir : mkdir creates a directory with each given name\nrmdir : rmdir removes each given empty directory\ncat   : Concatenate files and print on the standard output\nmore  : Let us display and scroll down the output in one direction only\n>     : Redirect the output to be written to a file\n>>    : Redirect the output to be written to a file  create/append\ncd    : This command changes the current directory to another one\nclear : This command can be called to clear the current terminal screen");
		
		 
	}
    ///////////////////////////////////////////////////ARGS/////////////////////////////////////////////////////////////////////

	public void args (String x)
	{
		Scanner bScanner=new Scanner(System.in);
		//String xString=bScanner.next();
		//String[] xString=bScanner.nextLine().split(" ");
		
		if (x.equals("ls")) {
			System.out.println("Number of args is 0 ");
		}
		else	if (x.equals("cd")) {
			System.out.println("Number of args is 1 , the name of directory");
		}
		else	if (x.equals("pwd")) {
			System.out.println("Number of args is 0 ");
		}
		else	if (x.equals("clear")) {
			System.out.println("Number of args is 0 ");
		}
		else if (x.equals("exit")) {
			System.out.println("Number of args is 0 ");
		}
		else if (x.equals("cp")) {
			System.out.println("Number of args is 2 , Source and Destination ");
		}
		else if (x.equals("mv")) {
			System.out.println("Number of args is 2 , Source and Destination ");
		}
		else if (x.equals("help")) {
			System.out.println("Number of args is 0 ");
		}
		else if (x.equals("args")) {
			System.out.println("Number of args is 1 , the command  ");
		}
		
		else if (x.equals("mkdir")) {
			System.out.println("Number of args is 1 , the directory  ");
		}
		else if (x.equals("rmdir")) {
			System.out.println("Number of args is 1 , the directory  ");
		}
		else if (x.equals("cat")) {
			System.out.println("Number of args is n , n: the number of text  ");
		}
		else if (x.equals("date")) {
			System.out.println("Number of args is 0  ");
		}
		
		
		
		
		
		
	}

	

}
