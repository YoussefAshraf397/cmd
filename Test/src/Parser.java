import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Parser {
	static String Arguments ="";
	static String cmd="";
	static String Defualt_Path="C:\\Users\\Yousef Badr\\Desktop\\Default_Path";
	
public static void main (String args[]) throws IOException  {
	
	System.out.println("\t\t\t\t\t\t\t   << Welcome to Command Line Interpreter >>");
	while (true) {
		System.out.print(">");
		Scanner bScanner=new Scanner(System.in);
		String p=bScanner.nextLine();
		Parse(p);	
	}
	

	
	}
static boolean Parse(String line) throws IOException
{
	Terminal T=new Terminal();
	int Number_Of_pipeoperator=0,N=0;
	String Cmd_With_Argument[]=new String[50];
    for (int i = 0; i < Cmd_With_Argument.length; i++) {Cmd_With_Argument[i]="";}
    
    
	for (int i = 0; i < line.length(); i++) {
		if(line.charAt(i)=='|') {Number_Of_pipeoperator++;N++;continue;}
		Cmd_With_Argument[N]+=line.charAt(i);
	}
	
	for (int i = 0; i < Cmd_With_Argument.length; i++) {
		if(Cmd_With_Argument[i]=="")break;
		cmd="";
		Arguments="";
		for (int j = 0; j < Cmd_With_Argument[i].length(); j++) {
			if(Cmd_With_Argument[i].charAt(j)==' ')
			{
				for(int d=j+1;d<Cmd_With_Argument[i].length();d++)
					Arguments+=Cmd_With_Argument[i].charAt(d);	
				break;
			}
			cmd+=Cmd_With_Argument[i].charAt(j);
		}
		
		if (line.contains(">") ) 
		{
			String s="";
			if(line.contains(">>"))
				{Terminal.Output_Port=2;s=">> ";}
			else
				{Terminal.Output_Port=1;s="> ";}
			
			
			String[]Output_file=Arguments.split(s);
			Arguments = Output_file[0];
			
			if(!Arguments.equals(""))
				Arguments=Arguments.substring(0, Arguments.length() - 1);
			
				
			//System.out.println("%"+Arguments+"%");
			File f = new File(Output_file[Output_file.length-1]);
			
			if(f.exists() && !f.isDirectory()) { 
				Terminal.Outpot_File_Location=Output_file[Output_file.length-1];
			}
			else
			{
				String temp="";
				temp+=Defualt_Path+ "\\" +Output_file[Output_file.length-1];
				Terminal.Outpot_File_Location=temp;
			}
			
			//System.out.println("%"+Terminal.Outpot_File_Location+"%");
			
			//ls C:\Users\Yousef Badr\Desktop\Default_Path\File/3> Test.txt
		}
			
		
		// ls C:\Users\Dell\Desktop\Defualt_Directory | clear | cdC:\Users\Dell\Desktop\Defualt_Directory
		//cmd===> ls , clear , cd
		//argument===> C:\Users\Dell\Desktop\Defualt_Directory , C:\Users\Dell\Desktop\Defualt_Directory
		
		if(cmd.equals("clear") )
		{
		if (Arguments.equals(""))
			T.Clear();
		else
			System.out.println("Error !");	
       
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////
		else if(cmd.equals("ls"))
		{
			if(Arguments.equals(""))
				T.ls(Defualt_Path);
			else {
				Arguments= Arguments.replace("/", " ");
				T.ls(Arguments);
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////
		else if (cmd.equals("cd")) {
			if(Arguments.equals(""))
				System.out.println("Error !");
			else
			{
				Arguments= Arguments.replace("/", " ");
				Defualt_Path=T.cd(Arguments);
				System.out.println("Default Path Successfully Changed To: "+Arguments );
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////

		else if (cmd.equals("rm")) {
			if(Arguments.equals(""))
				System.out.println("Error !");
			else
			{
				Arguments= Arguments.replace("/", " ");
			
               	File f = new File(Arguments);
				if(f.exists() && !f.isDirectory()) { 
					T.rm(Arguments);
				}
				else
				{
					String temp="";
					temp+=Defualt_Path+ "\\" +Arguments;
					
					T.rm(temp);

				}
				
				
			}
			
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////

		
		else if (cmd.equals("mkdir")){
			if(Arguments.equals(""))
				System.out.println("Error !");
			else
			{
				String temp="";
				Arguments= Arguments.replace("/", " ");

				temp+=Defualt_Path+ "\\" +Arguments;
				T.mkdir(temp);
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////

		else if (cmd.equals("rmdir")){
			if(Arguments.equals(""))
				System.out.println("Error !");
			else
			{
				String temp="";
				Arguments= Arguments.replace("/", " ");

				temp+=Defualt_Path+ "\\" +Arguments;
				T.rmdir(temp);
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////

		else if (cmd.equals("pwd"))
		{
			
			if(Arguments.equals("")) 
				T.pwd();
			else
				System.out.println("Error !");
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////

		
		else if (cmd.equals("more"))
				{
			/*pos dh eh ele py7sl e7na mfrod 
			 * EX: joe.txt ==> Argument   mla2a4 el pta3 dh 34an el path nfso m4 hwa ele mwgod feh el file
			 * hn3ml eh ??   hnlza2 apleh el default director pt3na ele mfrod kan yp2a feh el txt
			 */
			if(Arguments.equals(""))
				System.out.println("Error !");
			else
			{
				Arguments= Arguments.replace("/", " ");

               	File f = new File(Arguments);
				if(f.exists() && !f.isDirectory()) { 
					try {
						T.more(Arguments);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					String temp="";
					temp+=Defualt_Path+ "\\" +Arguments;
					try {
						T.more(temp);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				
			}
			
			
		}
		///////////////////////////////////////////////////////////////////////////////////////
		else if (cmd.equals("cp")) {
			if(Arguments.equals("")) 
				System.out.println("Error !");
			else
			{
				String[]Source_And_Destination=Arguments.split(" ");
				Source_And_Destination[0]= Source_And_Destination[0].replace("/", " ");
				Source_And_Destination[1]= Source_And_Destination[1].replace("/", " ");
				
				
	               	File f = new File(Source_And_Destination[0]);
					if(f.exists() && !f.isDirectory()) { 
						T.cp(Source_And_Destination[0], Source_And_Destination[1]);
					}
					else
					{
						String temp="";
						temp+=Defualt_Path+ "\\" +Source_And_Destination[0];
						T.cp(temp, Source_And_Destination[1]);
					}
					
					
				
				

				
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////
			
		else if (cmd.equals("mv"))
		{
			if(Arguments.equals("")) 
				System.out.println("Error !");
			else
			{
				String[]Source_And_Destination=Arguments.split(" ");
				Source_And_Destination[0]= Source_And_Destination[0].replace("/", " ");
				Source_And_Destination[1]= Source_And_Destination[1].replace("/", " ");
				
				
	               	File f = new File(Source_And_Destination[0]);
					if(f.exists() && !f.isDirectory()) { 
						T.cp(Source_And_Destination[0], Source_And_Destination[1]);
						T.rm(Source_And_Destination[0]);
					}
					else
					{
						String temp="";
						temp+=Defualt_Path+ "\\" +Source_And_Destination[0];
						T.cp(temp, Source_And_Destination[1]);
						T.rm(temp);
					}
				
			}
			
			
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////

		else if (cmd.equals("date"))
		{
			T.date();
			
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////
		else if(cmd.equals("cat"))
		{
			String[]Store=Arguments.split(" ");
			int spaces=0;
			for (int i1 = 0; i1 < Arguments.length(); i1++) 
			{
			
				if (Arguments.charAt(i1)==' ') 
				{
					spaces++;
				
				}
			}
			if(cmd.equals(Cmd_With_Argument[i]))
				System.out.println("Error !");
			else
			{
			
			//	System.out.println(spaces);
				for(int i2=0 ; i2<=spaces ; i2++)
				{
					
					
				Store[i2]= Store[i2].replace("/", " ");

               	File f = new File(Store[i2]);
				if(f.exists() && !f.isDirectory()) { 
					try {
						T.cat(Store[i2]);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					String temp="";
					temp+=Defualt_Path+ "\\" +Store[i2];
					try {
						T.cat(Store[i2]);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				
			//	System.out.println(Store[i2]);
				}
				
				}
			
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////
		else if (cmd.equals("help"))
		{
			T.help();
			
        }
		/////////////////////////////////////////////////////////////////////////////////////////////////

		else if(cmd.equals("args"))
		{
			T.args(Arguments);
			
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////
		else {
			System.out.println("There isn't a matching Command !");
		}
				
		
	Terminal.Output_Port=0;	
	}

	return true;
}

}


