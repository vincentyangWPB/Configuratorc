package ConfiguratorPackage;

public class Orientation{
	private String oName;
	private double oCost;
	private static final String defaultName= "DEFAULT";
	private static double defaultCost = 0.0;
	
	public Orientation() {
		this(defaultName, defaultCost);
	}
	
	public Orientation(String oName, double oCost) {
	 	setoName(oName);
	 	setoCost(oCost);
 	}
 
 	public void setoName(String oName){
 		oName = oName.toUpperCase();
 		//FS, WR, WL, WW
 		if(oName.equals("FS")||oName.equals("WR")||oName.equals("WL")||oName.equals("WW")||oName.equals("DEFAULT")) {
 			this.oName= oName;
 		}
 		else {
	 			throw new IllegalArgumentException("Invalid name");
	 		}
 	}
 		
 	
 	
 	public String getoName() {
 		return this.oName;
 	}
 	
 	public void setoCost(double oCost) {
 		if(oCost < 0) {
 			throw new IllegalArgumentException("Cost can not be negative");
 		}
 		else {	
 			this.oCost=oCost;
 		}
 	}

 	public double getoCost() {
 		return this.oCost;
 	}
}
