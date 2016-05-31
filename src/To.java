import java.util.ArrayList;

public class To extends Path{
	
	public String destNodeName;
	public boolean usePassingRestriction = false;
	public boolean toPass = true; // if false is notToPass
	public ArrayList<String> restrictionPathNodes = new ArrayList<>();
	
	public To(){
		
	}
	
	@Override
	public String toString() {
		String holder = sourceNodeName + "#" + destNodeName + "%";
		if(toMinimize) holder += "minimize";
		else holder += "maximize";
		holder += "-" + attributeFocus + "%";
		if(usePassingRestriction){
			if(toPass) holder += "Pass->";
			else holder += "NotPass->";
			
			for(String s : restrictionPathNodes)
				holder += s + ">";
		}
		return holder.substring(0, holder.length() - 1);
	}
}