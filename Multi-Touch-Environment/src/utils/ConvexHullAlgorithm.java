package utils;
import java.util.ArrayList;
import org.newdawn.slick.geom.Vector2f;


public interface ConvexHullAlgorithm 
{
	public ArrayList<Vector2f> execute(ArrayList<Vector2f> points);
}
