package edu.wgu.sbro323;

public class Inhouse extends Part {

	private int machineID;
        
        Inhouse(String name, Integer partID, Double price, Integer instock, Integer min, Integer max, int machineID){
            super(name, partID, price, instock, min, max);
            this.machineID = machineID;
        }

	public int getMachineID() {
		return machineID;
	}

	public void setMachineID(int machineID) {
		this.machineID = machineID;
	}
}
