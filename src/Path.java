import java.util.ArrayList;

public class Path {
	
	public String sourceNodeName;
	public boolean toMinimize; // if false is toMaximize
	public String attributeFocus;
	
	// used only in To and Cycle classes
	public boolean usePassingRestriction = false;
	public boolean toPass = true; // if false is notToPass
	public ArrayList<String> restrictionPathNodes = new ArrayList<>();
	
	public Path(){
	}
}