package plagiarismdetect;

import java.util.Comparator;
import java.util.LinkedList;

import plagiarismdetect.SourceComparator;
import plagiarismdetect.SourceReader;


public class SourceComparator 
{	
	private SourceReader file1;
	private SourceReader file2;
	private Double distance;
	private Double newDistance;

	public SourceComparator(SourceReader file1, SourceReader file2)
	{
		this.file1 = file1;
		this.file2 = file2;
	}
	
	public void calculateSimpleSimilarity()
	{
		getSimpleSimilarity();
	}
	
	public void calculateComplexSimilarity()
	{
		getComplexSimilarity();
	}
	
	
	
	public double getComplexSimilarity()
	{
		if (newDistance == null) newDistance = distance(file1.getNewSource(), file2.getNewSource());
		return newDistance;
	}
	
	public double getSimpleSimilarity()
	{
		if (distance == null) distance = distance(file1.getSource(), file2.getSource());
		return distance;
	}
	
	public double getVariableMatch()
	{
		return stringArrayMatch(file1.getVariables(), file2.getVariables());
	}
	
	public double getTypesMatch()
	{
		return stringArrayMatch(file1.getTypes(), file2.getTypes());
	}
	
	public double getCommentMatch()
	{
		return stringArrayMatch(file1.getComments(), file2.getComments());
	}	
	
	public double getMethodMatch()
	{
		return stringArrayMatch(file1.getMethods(), file2.getMethods());
	}
	
	
	public String getFile1Path()
	{
		return file1.getFilePath();
	}
	
	public String getFile2Path()
	{
		return file2.getFilePath();
	}
	
	public String getFile1Name()
	{
		return file1.getFileName();
	}
	
	public String getFile2Name()
	{
		return file2.getFileName();
	}

	public double stringArrayMatch(String[] a, String[] b)
	{
		if (a.length == 0 || b.length == 0) return 0;
		
		//Get bigger array
		if (b.length > a.length)
		{
			String[] c = a;
			a = b;
			b = c;
		}
		
		//Put in LinkedList for comparison
		LinkedList<String> holder = new LinkedList<>();
		for (String s : a)
		{
			holder.add(s);
		}
		
		//Check matches
		int matches = 0;
		for (String s : b)
		{
			if (holder.contains(s)) matches++;
		}
		
		return (matches/(double)a.length)*100;
	}

	
	//Lifted from http://rosettacode.org/wiki/Levenshtein_distance#Java
	//As such, this software is released under GNU General Public License  ( http://www.gnu.org/licenses/gpl.html )
    public static double distance(String a, String b) 
    {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        double distance = costs[b.length()];
        double total = a.length();
        if (b.length() > a.length()) total = b.length();
        return 100 - (Math.abs(distance)/(total))*100;
    }
	
    public static class ComparatorComparator implements Comparator<SourceComparator>
    {

			@Override
			public int compare(SourceComparator s1, SourceComparator s2) 
			{
				return (int)((s1.getComplexSimilarity()-s2.getComplexSimilarity())*1000000000);
				
			}
    
    }
}
