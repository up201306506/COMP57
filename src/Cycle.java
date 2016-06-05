
public class Cycle extends Path {

	public boolean canRepeatNodes; // if true is simplecycle, if false is cycle
	public int minLength;

	public Cycle(){

	}

	@Override
	public String toString() {
		
		String holder = "---------------------\n";
		holder += "src " + sourceNodeName + " ";
		if(canRepeatNodes) holder += "simplecycle";
		else holder += "cycle";
		holder += " minlength " + minLength + " " + attributeFocus;
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
		
		holder += "---------------------\n";
		if(canRepeatNodes){
			
			//Set pattern as a Cycle
			holder += "MATCH path = (src:Place { name:'"
			+sourceNodeName+ "'})-[r:ROAD*1..100]-(src:Place { name:'"+sourceNodeName+"'})\n";
			
			
			
			/*
			String temp = "simplecycle";
			holder += sourceNodeName + "#ml=" + minLength + "%" + temp;
			holder += "-" + attributeFocus + "%";
			if(usePassingRestriction){
				if(toPass) holder += "Pass->";
				else holder += "NotPass->";

				for(String s : restrictionPathNodes)
					holder += s + ">";
			}
			*/
			return holder.substring(0, holder.length() - 1);
		}else{
			
			//Set pattern as a Cycle
			holder += "MATCH path = (src:Place { name:'"
			+sourceNodeName+ "'})-[r:ROAD*1..100]-(src:Place { name:'"+sourceNodeName+"'})\n";
			//Dictate minimumlength
			holder += "WHERE length(path)>="+minLength+"\n";
			//Force single occurrence of every in-path node for ALL nodes
			holder += "AND ";
			holder += "ALL(x IN tail(nodes(path)) ";
			holder += "WHERE SINGLE(y IN tail(nodes(path)) WHERE x=y))\n";
			// Return values
			holder += "RETURN path,\n";
			// Minimize length sing given attribute
			holder += "reduce(" + attributeFocus + "=0, r in relationships(path) | "
				+ attributeFocus + "+r." + attributeFocus + ") AS total" + attributeFocus + "\n";
			holder += "ORDER BY total" + attributeFocus + ASC"\n";
			// Limiting result count
			holder += "LIMIT 1\n";
			
			
			/*
			String temp = "cycle";
			holder += sourceNodeName + "#ml=" + minLength + "%" + temp;
			holder += "-" + attributeFocus + "%";
			if(usePassingRestriction){
				if(toPass) holder += "Pass->";
				else holder += "NotPass->";

				for(String s : restrictionPathNodes)
					holder += s + ">";
			}
			*/
			
			return holder.substring(0, holder.length() - 1);
		}
	}
}