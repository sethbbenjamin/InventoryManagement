package edu.wgu.sbro323;

public class Inhouse extends Part {

	private int machineID;
        
//        Inhouse(){
//            super();
//            this.machineID = 0;
//        }
        
        Inhouse(String name, Double price, int instock, int min, int max, int machineID) throws InvalidInventoryException{
            super(name, price, instock, min, max);
            this.machineID = machineID;
        }

	public int getMachineID() {
		return machineID;
	}

	public void setMachineID(int machineID) {
		this.machineID = machineID;
	}
}
