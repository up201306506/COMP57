
public class To extends Path{

	public String destNodeName;

	public To(){

	}

	@Override
	public String toString() {

		// original querry
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

		// Cypher Translation		
		String pathString = String.format(
				"MATCH  p=(src:Place { name:'%s'})-[:ROAD*1..100]->(dest:Place { name:'%s'})\n", 
				sourceNodeName, destNodeName);
		holder += pathString;
		
		if(usePassingRestriction){
			for(String s : restrictionPathNodes)
				holder += ",(node"+s+":Place { name:'"+s+"' })\n";
		}
		
		holder += "WHERE ALL(n in nodes(p) where 1=size(filter(m in nodes(p) WHERE m=n)))";
		
		if(usePassingRestriction){
			if(!toPass)
				for(String s : restrictionPathNodes)
					holder +=  "AND NONE (n IN nodes(p) WHERE n=node"+s+")\n";
		}
		
		holder += "RETURN p AS path,\n";
		holder += "reduce(" + attributeFocus + "=0, r in relationships(p) | "
				+ attributeFocus + "+r." + attributeFocus + ") AS total" + attributeFocus + "\n";
		holder += "ORDER BY total" + attributeFocus + " ";
		if(toMinimize) holder += "ASC\n";
		else holder += "DESC\n";
		
		holder += "LIMIT 1";

		/*
		//Original code block

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
		 */

		return holder;
	}
}