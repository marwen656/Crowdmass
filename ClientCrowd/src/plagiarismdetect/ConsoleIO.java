package plagiarismdetect;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import plagiarismdetect.Language;
import plagiarismdetect.MyMain;
import plagiarismdetect.SourceComparator;
import plagiarismdetect.SourceReader;
import plagiarismdetect.Utilities;


public class ConsoleIO {	
	private static final boolean  DEBUGGING_MODE = false;
	static String Value;
	static String pourcentage;
	static String value1;
	static String value2;
	static String value4;
	static String value3;
	static String value5;
	static String resultat;
	static String res=null;
	static String pers=null;
	
	
	private static class Output
	{
		private static void version()
		{
			System.out.println("Version "+MyMain.VERSION);
		}
		
		private static void usage()
		{
			System.out.println("... Plagiarism.jar [-version] <directory>");
			System.out.println("Type 'java -jar Plagiarism.jar help' for more information.");
		}
		
		private static void help()
		{
			System.out.println("PlagiarismDetect Help\n\n"
					+"Usage: java -jar Plagiarism.jar [-version] <directory>\n"
					+"Compares Java source code (.java files) in specified directory and System.out.printlns similarity.\n"
					+"\nFlags:\n"
					+"\t-version\tDisplays version information.\n"
					+"\nParameters:\n"
					+"\tdirectory\tTarget directory to search (recursively) for source files.");
		}
	}
	

	
	public ConsoleIO(String args[])
	{
		run(args);
	}
	

	
	public static String run(String args[])
	{
	
		Scanner kboard = new Scanner(System.in);
		
		if (DEBUGGING_MODE)
		{
			System.out.println("> ");
			args = new String[]{kboard.nextLine()};
		}
			
		
		if (args.length < 1 || args[0].contains("?")) Output.usage();
		else if (args[0].equals("help")) Output.help();
		else
		{
			if (args[0].equals("-version")) Output.version();
			else
			{
				File directory = new File(args[0]);
				if (!directory.exists())
				{
					System.out.println("The specified directory \""+directory+"\" does not exist!");
					resultat=("The specified directory \""+directory+"\" does not exist!");
					
					kboard.close();
					
				}
				
				System.out.println("Searching recursively for Java source files...");	
				value1=("Searching recursively for Java source files...");
				Language lang = Language.JAVA;
				SourceReader[] sourceFiles = Utilities.getSourceFiles(lang, directory);
				
				System.out.println("Loaded "+sourceFiles.length+" "+lang.getName()+" files.");
				value2=("Loaded "+sourceFiles.length+" "+lang.getName()+" files.");
				if (sourceFiles.length < 1)
				{
					System.out.println("You need atleast two files to make a comparison!");
					kboard.close();
					value3=("You need atleast two files to make a comparison!");
					
				}
				
				SourceComparator[] matches = Utilities.getComparisons(sourceFiles);
				
				double percentage = -1;
			
				for (int i = 0; i < matches.length; i++)
				{
					matches[i].calculateComplexSimilarity();
					
					double newPercentage = ((double)(i+1)/matches.length)*100;
					if ((int)newPercentage > (int)percentage)
					{
						percentage = newPercentage;
						System.out.print("\rComparing source files... ["+(int)percentage+"%]");
						pourcentage=("\rComparing source files... ["+(int)percentage+"%]");
						pers+=percentage;
					
						
					}
				}
				Collections.sort(Arrays.asList(matches), new SourceComparator.ComparatorComparator());
				System.out.println("");
				
				for (int i = 0; i < matches.length; i++)
				{
					SourceComparator match = matches[i];
					System.out.println(
							"\nComparison "+ (i+1) +": \t"+String.format("%10.2f",match.getComplexSimilarity())+"% weighted similarity.\n"+
							"\tFile 1:\t"+match.getFile1Path()+"\n"+
							"\tFile 2:\t"+match.getFile2Path());
					
					//affichage en tableau
					Value=("\nComparison "+ (i+1) +": \t"+String.format("%10.2f",match.getComplexSimilarity())+"% weighted similarity.\n"+
							"\tFile 1:\t"+match.getFile1Path()+"\n"+
							"\tFile 2:\t"+match.getFile2Path());
					res+=Value;
					
				}
				
				
					
			}

		}
		kboard.close();
		resultat=value1+"\t"+value2 +"\t"+"\t"+res +"\t";
		return(resultat);
	}
	

}
