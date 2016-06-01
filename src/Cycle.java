
public class Cycle extends Path {

	public boolean canRepeatNodes; // if true is simplecycle, if false is cycle
	public int minLength;

	public Cycle(){

	}

	@Override
	public String toString() {
		
		String holder = "";
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
		if(canRepeatNodes){
			String temp = "simplecycle";

			holder += sourceNodeName + "#ml=" + minLength + "%" + temp;
			holder += "-" + attributeFocus + "%";
			if(usePassingRestriction){
				if(toPass) holder += "Pass->";
				else holder += "NotPass->";

				for(String s : restrictionPathNodes)
					holder += s + ">";
			}
			
			return holder.substring(0, holder.length() - 1);
		}else{
			String temp = "cycle";

			holder += sourceNodeName + "#ml=" + minLength + "%" + temp;
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
}