package plagiarismdetect;

import plagiarismdetect.Language;


public class Language {
	public static final Language JAVA = new Language("Java",".java",";","((?!package|return)\\b\\w+(?=(?=\\s+\\w+\\s+=)|(?=\\s+\\w+;)))","(\\b\\S+\\b(?=\\s*=))","(?<=\\\\)(.+)", "(?<=class\\b\\s)(\\w+)", "(?<=.\\s)(?<!new\\s)(\\w+(?=\\())");
	public static final Language PLAINTEXT = new Language("Plaintext", ".", null, null, null, null, null, null);

	
	
	private String name;
	private String extension;
	private String lineBreak;
	private String variableRegex;
	private String typeRegex;
	private String commentRegex;
	private String classRegex;
	private String methodRegex;
	
	Language(String name, String fileExtension, String lineBreak, String typeRegex, String variableRegex, String commentRegex, String classRegex, String methodRegex)
	{
		this.name = name;
		this.extension = fileExtension;
		this.lineBreak = lineBreak;
		this.typeRegex = typeRegex;
		this.variableRegex = variableRegex;
		this.commentRegex = commentRegex;
		this.classRegex = classRegex;
		this.methodRegex = methodRegex;
	}
	
	String getName()
	{
		return name;
	}
	
	String getFileExtension()
	{
		return extension;
	}
	
	String getLineBreak()
	{
		return lineBreak;
	}
	
	String getMethodRegex()
	{
		return methodRegex;
	}
	
	String getVariableRegex()
	{
		return variableRegex;
	}
	
	String getTypeRegex()
	{
		return typeRegex;
	}
	
	String getCommentRegex()
	{
		return commentRegex;
	}
	
	String getClassRegex()
	{
		return classRegex;
	}
}
