package edu.wgu.sbro323;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Part {

	private String name;
	private Integer partID;
	private Double price;
	private Integer instock;
	private Integer min;
	private Integer max;
	
	
        Part(){
            
        }
        
	Part(String name, Integer partID, Double price, Integer instock, Integer min, Integer max){
            this.name = name;
            this.partID = partID;
            this.price = price;
            this.instock = instock;
            this.min = min;
            this.max = max;
        }
	
	public String getName() {
		return name;
	}

        
	public void setName(String name) {
		this.name = name;
	}
	public int getPartID() {
		return partID;
	}
	public void setPartID(int partID) {
		this.partID = partID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getInstock() {
		return instock;
	}
	public void setInstock(int instock) {
		this.instock = instock;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
}
