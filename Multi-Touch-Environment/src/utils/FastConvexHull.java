package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.newdawn.slick.geom.Vector2f;

public class FastConvexHull implements ConvexHullAlgorithm
{

    
    
        
    
        @Override
	public ArrayList<Vector2f> execute(ArrayList<Vector2f> points) 
	{
		ArrayList<Vector2f> xSorted = (ArrayList<Vector2f>) points.clone();
		Collections.sort(xSorted, new XCompare());
		
		int n = xSorted.size();
		
		Vector2f[] lUpper = new Vector2f[n];
		
		lUpper[0] = xSorted.get(0);
		lUpper[1] = xSorted.get(1);
		
		int lUpperSize = 2;
		
		for (int i = 2; i < n; i++)
		{
			lUpper[lUpperSize] = xSorted.get(i);
			lUpperSize++;
			
			while (lUpperSize > 2 && !rightTurn(lUpper[lUpperSize - 3], lUpper[lUpperSize - 2], lUpper[lUpperSize - 1]))
			{
				// Remove the middle point of the three last
				lUpper[lUpperSize - 2] = lUpper[lUpperSize - 1];
				lUpperSize--;
			}
		}
		
		Vector2f[] lLower = new Vector2f[n];
		
		lLower[0] = xSorted.get(n - 1);
		lLower[1] = xSorted.get(n - 2);
		
		int lLowerSize = 2;
		
		for (int i = n - 3; i >= 0; i--)
		{
			lLower[lLowerSize] = xSorted.get(i);
			lLowerSize++;
			
			while (lLowerSize > 2 && !rightTurn(lLower[lLowerSize - 3], lLower[lLowerSize - 2], lLower[lLowerSize - 1]))
			{
				// Remove the middle point of the three last
				lLower[lLowerSize - 2] = lLower[lLowerSize - 1];
				lLowerSize--;
			}
		}
		
		ArrayList<Vector2f> result = new ArrayList<Vector2f>();
		
		for (int i = 0; i < lUpperSize; i++)
		{
			result.add(lUpper[i]);
		}
		
		for (int i = 1; i < lLowerSize - 1; i++)
		{
			result.add(lLower[i]);
		}
		
		return result;
	}
	
	private boolean rightTurn(Vector2f a, Vector2f b, Vector2f c)
	{
		return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x) > 0;
	}

	private class XCompare implements Comparator<Vector2f>
	{
		@Override
		public int compare(Vector2f o1, Vector2f o2) 
		{
			return (new Integer((int) o1.x)).compareTo(new Integer((int) o2.x));
		}
	}
}
