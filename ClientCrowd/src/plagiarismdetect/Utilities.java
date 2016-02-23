package plagiarismdetect;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import plagiarismdetect.Language;
import plagiarismdetect.SourceComparator;
import plagiarismdetect.SourceReader;
import plagiarismdetect.Utilities;

public class Utilities 
{
	
	private static LinkedList<File> getFilesRecursively(File directory)
	{
		LinkedList<File> allFiles = new LinkedList<>();
		if (directory.isDirectory())
		{
			for (File f : directory.listFiles())
			{
				if (f.isDirectory()) allFiles.addAll(getFilesRecursively(f));
				else allFiles.add(f);
			}
		}
		return allFiles;
	}
	
	
	public static SourceComparator[] getComparisons(SourceReader[] sourceFiles)
	{
		LinkedList<SourceComparator> matches = new LinkedList<>();
		for (int i = 0; i < sourceFiles.length; i++)
		{
			for (int j = i+1; j < sourceFiles.length; j++)
			{
				SourceComparator sc = new SourceComparator(sourceFiles[i], sourceFiles[j]);
				matches.add(sc);
			}
		}
		return matches.toArray(new SourceComparator[0]);
	}
	
	
	public static SourceReader[] getSourceFiles(Language lang, File directory)
	{
		if (directory.exists())
		{
			if (directory.isDirectory())
			{
				LinkedList<File> allFiles = Utilities.getFilesRecursively(directory);
				LinkedList<SourceReader> sourceFiles = new LinkedList<SourceReader>();
				for (int i = 0; i<allFiles.size(); i++)
				{

					try
					{
						if (allFiles.get(i).getName().contains(lang.getFileExtension())) sourceFiles.add(new SourceReader(lang, allFiles.get(i)));
					}
					catch(FileNotFoundException e)
					{
						System.out.println("Could not find file: "+allFiles.get(i).getPath());
						continue;
					}

					
				}
				return sourceFiles.toArray(new SourceReader[0]);
			}
		}
		return new SourceReader[0];
	}	
	
	
}
