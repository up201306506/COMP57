
public class To extends Path{

	public String destNodeName;

	public To(){

	}

	@Override
	public String toString() {

		// original querry
		String holder = "---------------------\n";
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

		//-------------------
		// Cypher Translation
		//-------------------
		holder += "---------------------\n";
		
		//Define the Path
		String pathString = String.format(
				"MATCH  p=(src:Place { name:'%s'})-[:ROAD*1..100]->(dest:Place { name:'%s'})\n", 
				sourceNodeName, destNodeName);
		holder += pathString;
		//Define restriction nodes
		if(usePassingRestriction){
			for(String s : restrictionPathNodes)
				holder += ",(node"+s+":Place { name:'"+s+"' })\n";
		}
		//Prevent Backtracking
		holder += "WHERE ALL(n IN nodes(p) WHERE 1=size(filter(m in nodes(p) WHERE m=n)))\n";
		//Define Restriction Type
		if(usePassingRestriction){
			if(toPass)
				for(String s : restrictionPathNodes)
					holder +=  "AND node"+s+" IN nodes(p)\n";
			else
				for(String s : restrictionPathNodes)
					holder +=  "AND NONE (n IN nodes(p) WHERE n=node"+s+")\n";
				
		}
		//RETURN values
		holder += "RETURN p AS path,\n";
		//Define min/max through return ordering
		holder += "reduce(" + attributeFocus + "=0, r in relationships(p) | "
				+ attributeFocus + "+r." + attributeFocus + ") AS total" + attributeFocus + "\n";
		holder += "ORDER BY total" + attributeFocus + " ";
		if(toMinimize) holder += "ASC\n";
		else holder += "DESC\n";
		//Number of paths returned
		holder += "LIMIT 1";

		return holder;
	}
}