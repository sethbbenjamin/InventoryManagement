package edu.wgu.sbro323;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Part {

	private final StringProperty name;
	private final IntegerProperty partID;
	private final DoubleProperty price;
	private final IntegerProperty instock;
	private final IntegerProperty min;
	private final IntegerProperty max;
        
        private static int nextID = 100;
	
	
        Part(){
           name  = new SimpleStringProperty();
           partID = new SimpleIntegerProperty(generateNextID());
           price = new SimpleDoubleProperty();
           instock = new SimpleIntegerProperty();
           min = new SimpleIntegerProperty();
           max = new SimpleIntegerProperty();
        }
        
	Part(String name, Double price, Integer instock, Integer min, Integer max){
           this.name  = new SimpleStringProperty(name);
           this.partID = new SimpleIntegerProperty(generateNextID());
           this.price = new SimpleDoubleProperty(price);
           this.instock = new SimpleIntegerProperty(instock);
           this.min = new SimpleIntegerProperty(min);
           this.max = new SimpleIntegerProperty(max);
        }
        
	private int generateNextID(){
            return ++nextID;
        }
        
	public String getName() {return name.get();} 
        public void setName(String name){this.name.set(name);}
        public StringProperty nameProperty(){return name;}
	
        
        public int getPartID() {return partID.get();}
	public void setPartID(int partID) {this.partID.set(partID);}     
        public IntegerProperty PartIDProperty(){ return partID; }
        
	public double getPrice() {return price.get();}
	public void setPrice(double price) {this.price.set(price);}
	public DoubleProperty priceProperty(){ return price;}
        
        public int getInstock() {return instock.get();}
	public void setInstock(int instock) {this.instock.set(instock);}
	public IntegerProperty instockProperty(){ return instock;}
        
        
        public int getMin() {return min.get();}
	public void setMin(int min) {this.min.set(min);}
	public IntegerProperty minProperty(){ return min;}
        
        public int getMax() {return max.get();}
	public void setMax(int max) {this.max.set(max);}
	public IntegerProperty maxProperty(){return max;}

}
