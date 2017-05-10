package edu.wgu.sbro323;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Part implements InventoryItem {

	private final StringProperty name;
	private final IntegerProperty partID;
	private final DoubleProperty price;
	private final IntegerProperty instock;
	private final IntegerProperty min;
	private final IntegerProperty max;
                
        private static int nextID = 3000;
               
        
	Part(String name, Double price, Integer instock, Integer min, Integer max) throws InvalidInventoryException{
           
            
           this.name = new SimpleStringProperty(); 
           this.partID = new SimpleIntegerProperty(generateNextID());
           this.price = new SimpleDoubleProperty();
           this.instock = new SimpleIntegerProperty();
           this.min = new SimpleIntegerProperty();
           this.max = new SimpleIntegerProperty();
           
           setName(name);
           setPrice(price);
           
            if ((instock < min) || (instock > max)) {
                throw new InvalidInventoryException("Inventory must be between min and max");
            } else if (min > max) {
                throw new InvalidInventoryException("Min must be less than max");
            } else {
                this.instock.set(instock);
                this.min.set(min);
                this.max.set(max);
            }
           
        }
        
       //Used for demonstration purposes. A more robust ID management system would be needed
        private int generateNextID(){
            return ++nextID;
        }
        
        //Name methods
        public final void setName(String name) {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name must not be blank");
            }
            this.name.set(name);
        }
        
        @Override
	public String getName() {return name.get();} 
        public StringProperty nameProperty(){return name;}
	
        
        public int getPartID() {return partID.get();}
	public void setPartID(int partID) {this.partID.set(partID);}     
        public IntegerProperty partIDProperty(){ return partID; }
        
        //Price methods
        public final void setPrice(double price) {
            if (price < 0) {
                throw new IllegalArgumentException("Price must not be negative");
            }
            this.price.set(price);
        }
        public double getPrice() {return price.get();}
	public DoubleProperty priceProperty(){ return price;}
        
        public int getInstock() {return instock.get();}
	
        public void setInstock(int instock) throws InvalidInventoryException {
            
            if ((instock < getMin()) || (instock > getMax())) {
                throw new InvalidInventoryException("Inventory must be between min and max");
            }
            
            this.instock.set(instock);
        }
	public IntegerProperty instockProperty(){ return instock;}
        
        
        //Min methods
        public int getMin() {return min.get();}
        
	public void setMin(int min) throws InvalidInventoryException {
            if (min > getMax()) {
                throw new InvalidInventoryException("Min must be less than max");
            }
            this.min.set(min);
        }
        
	public IntegerProperty minProperty(){ return min;}
        
        //Max methods
        public int getMax() {return max.get();}
	public void setMax(int max) throws InvalidInventoryException {
            if (max < getMin()) {
                throw new InvalidInventoryException("Max must be greater than min");
            }
            this.max.set(max);
        }
	public IntegerProperty maxProperty(){return max;}

}
