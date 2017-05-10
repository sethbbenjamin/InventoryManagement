package edu.wgu.sbro323;

public class Inhouse extends Part {

	private int machineID;
        
        
        Inhouse(String name, Double price, int instock, int min, int max, int machineID) throws InvalidInventoryException{
            super(name, price, instock, min, max);
            setMachineID(machineID);
        }

	public int getMachineID() {
		return machineID;
	}

	public void setMachineID(int machineID) {
            if(machineID < 0){
                throw new IllegalArgumentException("Invalid Machine ID");
            }
            this.machineID = machineID;
	}
}
