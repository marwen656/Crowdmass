package plagiarismdetect;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import plagiarismdetect.Language;
import plagiarismdetect.SourceReader;


public class SourceReader {
	static final String TYPE_PLACEHOLDER = "t";
	static final String VARIABLE_PLACEHOLDER = "v";
	static final String COMMENT_PLACEHOLDER = "//com ";
	static final String CLASS_PLACEHOLDER = "c";
	static final String METHOD_PLACEHOLDER = "m";

	private Language language;
	private File toRead;
	
	private String source;
	private String[] lines;
	private String[] sortedLines;
	private String[] variables;
	private String[] types;
	private String[] comments;
	private String[] classes;
	private String[] methods;
	private String cleanString;
	
	SourceReader(Language language, File toRead) throws FileNotFoundException
	{
		this.language = language;
		this.toRead = toRead;
		
		parseSource();		
	}
	
	private void parseSource() throws FileNotFoundException
	{
		//get source code
		source = readSource(toRead);

		//separate source in to lines and remove multiple whitespace
		lines = readSeparateLines(source);

		
		//get types 
		if (language.getTypeRegex() != null) types = readComponent(lines, language.getTypeRegex());
		else types = new String[0];
		
		//get variables
		if (language.getVariableRegex() != null) variables = readComponent(lines, language.getVariableRegex());
		else variables = new String[0];
		
		//get comments
		if (language.getCommentRegex() != null) comments = readComponent(lines, language.getCommentRegex());
		else comments = new String[0];
		
		//get classes
		if (language.getClassRegex() != null) classes = readComponent(lines, language.getClassRegex());
		else classes = new String[0];
		
		//TODO get methods
		if (language.getMethodRegex() != null) methods = readComponent(lines, language.getMethodRegex());
		
		
		//convert lines back to string
		String str = linesToString(lines);
	
		//replace types, variables, and comments with placeholders
		cleanString = cleanSource(str);
		
		//sortedLines = sortLines(readSeparateLines(cleanString));
		//cleanString = linesToString(sortedLines);
	}
	
	
	public Language getLanguage()
	{
		return language;
	}
	
	private String cleanSource(String source)
	{
		for (String com : comments)
		{
			source = source.replace(com, SourceReader.COMMENT_PLACEHOLDER);
		}
		
		for (String t : types)
		{
			source = source.replace(t, SourceReader.TYPE_PLACEHOLDER);
		}
		
		for (String v : variables)
		{
			source = source.replace(v, SourceReader.VARIABLE_PLACEHOLDER);
		}
		
		for (String c : classes)
		{
			source = source.replace(c,  SourceReader.CLASS_PLACEHOLDER);
		}
		
		for (String m : methods)
		{
			source = source.replace(m, SourceReader.METHOD_PLACEHOLDER);
		}
		
		return source;
		
	}
	
	private String linesToString(String[] sortedLines)
	{
		String toReturn = "";
		for (String s : sortedLines)
		{
			toReturn += s;
		}
		return toReturn;
	}
	
	
	@SuppressWarnings("unused")
	private String[] sortLines(final String[] toSort)
	{
		String[] arrayCopy = toSort.clone();
		Arrays.sort(arrayCopy);
		return arrayCopy;
	}
	
	
	@SuppressWarnings("resource")
	private String readSource(File read) throws FileNotFoundException
	{
		return new Scanner(read).useDelimiter("\\Z").next();
	}
	
	
	private String[] readSeparateLines(String toSeparate)
	{
		LinkedList<String> lines = new LinkedList<>();
		for (String s : toSeparate.split("\n"))
		{
			String x = (s.replaceAll("^\\s*", ""));
			if (x.length() > 1) lines.add(x+"\n");
		}
		return lines.toArray(new String[0]);
	}
	
	private String[] readComponent(String[] lines, String regex)
	{
		LinkedList<String> comp = new LinkedList<>();
		for (String s : getMatches(getLines(), regex))
		{
			comp.add(s);
		}
		String[] toReturn = comp.toArray(new String[0]);
		Arrays.sort(toReturn);
		return toReturn;
	}
	
	private String[] getMatches(String[] lines, String regex)
	{
		Pattern toMatch = Pattern.compile(regex);
		LinkedList<String> matches = new LinkedList<>();
		
		Matcher m;
		for (String line : lines)
		{
			m = toMatch.matcher(line);
			while (m.find())
			{
				matches.add(m.group());
			}
		}
		return matches.toArray(new String[0]);
	}
	
	public String getNewSource()
	{
		return cleanString;
	}
	
	String getFilePath()
	{
		return toRead.getPath();
	}
	
	String getFileName()
	{
		return toRead.getName();
	}

	String getSource()
	{
		return source;
	}
	
	
	private int getNumLines()
	{
		return lines.length;
	}
	
	int getNumChars()
	{
		return source.length();
	}
	
	
	String[] getLines()
	{
		return lines;
	}
	
	
	String[] getSortedLines()
	{
		return sortedLines;
	}
	
	
	String[] getVariables()
	{
		return variables;
	}
	
	String[] getTypes()
	{
		return types;
	}
	
	String[] getComments()
	{
		return comments;
	}
	
	
	String[] getMethods()
	{
		return methods;
	}
	
	void printTypes()
	{
		System.out.println("Types\n---------------------");
		for (String t : types)
		{
			System.out.println("\t"+t);
		}
	}
	
	void printVars()
	{
		System.out.println("Variables\n---------------------");
		for (String t : variables)
		{
			System.out.println("\t"+t);
		}		
	}
	
	void printComments()
	{
		System.out.println("Comments\n--------------------");
		for (String c : comments)
		{
			System.out.println("\t"+c);
		}
	}
	
	void printAll()
	{
		System.out.println(
				"\nSource code\n"+
				"----------------------------------------\n"+
				"Number lines:\t\t"+getNumLines()+"\n"+
				"Number chars:\t\t"+getNumChars());
		printTypes();
		printVars();
		printComments();
	}
	
	
}
