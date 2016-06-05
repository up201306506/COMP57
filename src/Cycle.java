
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
			+sourceNodeName+ "'})-[:ROAD*1..50]-(src:Place { name:'"+sourceNodeName+"'})\n";
			//Define restriction nodes
			if(usePassingRestriction){
				for(String s : restrictionPathNodes)
					holder += ",(node"+s+":Place { name:'"+s+"' })\n";
			}
			//Choose variables to use
			holder += "WITH path, src,reduce(" +attributeFocus + "=0, r in relationships(path) | "
				+ attributeFocus + "+r." + attributeFocus + ") AS total" + attributeFocus + "\n";
			if(usePassingRestriction){
				for(String s : restrictionPathNodes)
					holder += ",node"+s+"\n";
			}
			//Force single occurrence of every in-path node for ONLY THE SOURCE nodes
			holder += "WHERE SINGLE(y IN tail(nodes(path)) WHERE src=y)\n";
			//Force minimum value of focused variable
			holder += "AND total"+attributeFocus+" >= "+minLength+"\n";
			//Define Restriction Type
			if(usePassingRestriction){
				if(toPass)
					for(String s : restrictionPathNodes)
						holder +=  "AND node"+s+" IN nodes(path)\n";
				else
					for(String s : restrictionPathNodes)
						holder +=  "AND NONE (n IN nodes(path) WHERE n=node"+s+")\n";
					
			}
			// Return values
			holder += "RETURN path, total"+attributeFocus+"\n";
			//Order FIRST by reduction of variable, THEN largest path (in nodes)
			holder += "ORDER BY total"+attributeFocus+" ASC, length(path) DESC\n";
			// Limit result count
			holder += "LIMIT 1\n";
			
			return holder.substring(0, holder.length() - 1);
		}else{
			
			//Set pattern as a Cycle
			holder += "MATCH path = (src:Place { name:'"
			+sourceNodeName+ "'})-[:ROAD*1..50]-(src:Place { name:'"+sourceNodeName+"'})\n";
			//Define restriction nodes
			if(usePassingRestriction){
				for(String s : restrictionPathNodes)
					holder += ",(node"+s+":Place { name:'"+s+"' })\n";
			}
			//Choose variables to use
			holder += "WITH path, src,reduce(" +attributeFocus + "=0, r in relationships(path) | "
				+ attributeFocus + "+r." + attributeFocus + ") AS total" + attributeFocus + "\n";
			if(usePassingRestriction){
				for(String s : restrictionPathNodes)
					holder += ",node"+s+"\n";
			}
			//Force single occurrence of every in-path node for ALL nodes
			holder += "WHERE ";
			holder += "ALL(x IN tail(nodes(path)) ";
			holder += "WHERE SINGLE(y IN tail(nodes(path)) WHERE x=y))\n";
			//Force minimum value of focused variable
			holder += "AND total"+attributeFocus+" >= "+minLength+"\n";
			//Define Restriction Type
			if(usePassingRestriction){
				if(toPass)
					for(String s : restrictionPathNodes)
						holder +=  "AND node"+s+" IN nodes(path)\n";
				else
					for(String s : restrictionPathNodes)
						holder +=  "AND NONE (n IN nodes(path) WHERE n=node"+s+")\n";
					
			}
			// Return values
			holder += "RETURN path, total"+attributeFocus+"\n";
			//Order FIRST by reduction of variable, THEN largest path (in nodes)
			holder += "ORDER BY total"+attributeFocus+" ASC, length(path) DESC\n";
			// Limit result count
			holder += "LIMIT 1\n";
			
			return holder.substring(0, holder.length() - 1);
		}
	}
}