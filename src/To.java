
public class To extends Path{
	
	public String destNodeName;
	
	public To(){
		
	}
	
	@Override
	public String toString() {
		
		String holder = "";
		holder += "src " + sourceNodeName + " dest " + destNodeName + " ";
		if(toMinimize) holder += "minimize";
		else holder += "maximize";
		holder += " " + attributeFocus;
		if(usePassingRestriction){
			holder += " ";
			
			if(toPass) holder += "pass";
			else holder += "not pass";
			
			holder += " ";
			
			for(int i = 0; i < restrictionPathNodes.size() - 1; i++)
				holder += restrictionPathNodes.get(i) + ", ";
			
			holder += restrictionPathNodes.get(restrictionPathNodes.size() - 1);
		}
		holder += "\n";
		
		// TODO block To change
		holder += sourceNodeName + "#" + destNodeName + "%";
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