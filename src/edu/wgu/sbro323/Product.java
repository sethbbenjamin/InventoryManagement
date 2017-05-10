package edu.wgu.sbro323;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product implements InventoryItem {

    private ArrayList<Part> parts;
    private IntegerProperty productID;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty instock;
    private IntegerProperty min;
    private IntegerProperty max;
    
    //Used for demonstration purposes to create IDs
    private static int nextID = 200;

    
    Product(String name, Double price, Integer instock, Integer min, Integer max, ArrayList<Part> parts) throws InvalidInventoryException {
        
        this.name = new SimpleStringProperty();
        this.productID = new SimpleIntegerProperty(generateNextID());
        this.price = new SimpleDoubleProperty();
        this.instock = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
        this.parts = new ArrayList<>();
        
        setName(name);
        setProductID(generateNextID());
        
        if ((instock < min) || (instock > max)) {
            throw new InvalidInventoryException("Inventory must be between min and max");
        } else if (min > max) {
            throw new InvalidInventoryException("Min must be less than max");
        } else {
            this.instock.set(instock);
            this.min.set(min);
            this.max.set(max);
        }    
        
        if (validateParts(parts, price)){
            this.price.set(price);
            this.parts = parts;
        }

    }
    
    
    //Name methods
    @Override
    public String getName() {
        return name.get();
    }

    public final void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be blank");
        } else {
            this.name.set(name);
        }      
    }

    public StringProperty nameProperty() {
        return name;
    }
    
    
    //Price methods
    public double getPrice() {
        return price.get();
    }

    public final void setPrice(double price) throws InvalidInventoryException {
        
        if (validateParts(getParts(), price)){
            this.price.set(price);
        }
    }

    public DoubleProperty priceProperty() {
        return price;
    }
    
    
    //Instock methods
    
    public final void setInstock(int instock) throws InvalidInventoryException {
        
        if(instock < 0){
            throw new IllegalArgumentException("Inventory cannot be negative");
        } else if (instock < getMin() || instock > getMax()){
            throw new InvalidInventoryException("Inventory must be between min and max");
        }
        
        this.instock.set(instock);
    }
    
    public int getInstock() {
        return instock.get();
    }

    public IntegerProperty instockProperty() {
        return instock;
    }
    
    //Min methods
    public int getMin() {
        return min.get();
    }

    public final void setMin(int min) throws InvalidInventoryException {
        if(min < 0){
            throw new IllegalArgumentException("Min must not be negative");
        }
        if (min > getMax()){
            throw new InvalidInventoryException("Min must be less than max");
        }
        this.min.set(min);
    }

    public IntegerProperty minProperty() {
        return min;
    }
    
    //Max methods
    public int getMax() {
        return max.get();
    }

    public final void setMax(int max) throws InvalidInventoryException {
        
        if (max < 0) {
            throw new IllegalArgumentException("Max must not be negative");
        }
        if (max < getMin()) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        this.max.set(max);
    }

    public IntegerProperty maxProperty() {
        return max;
    }
    
    //part methods
    public void addPart(Part part) {
        this.parts.add(part);
    }

    public boolean removePart(int id) {
        return this.parts.remove(id) != null;
    }
    
    public Part lookupPart(int id) {
        return this.parts.get(id);
    }
    
    public void updatePart(Part part) {
        int index = this.parts.indexOf(part);
        this.parts.set(index, part);
    }
    
    //productID methods
    public int getProductID() {
        return productID.get();
    }

    public final void setProductID(int productID) {
        this.productID.set(productID);
    }

    public IntegerProperty productIDProperty() {
        return productID;
    }
    
    //setter / getters
    public ArrayList<Part> getParts() {return parts;}
    
    
    public final void setParts(ArrayList<Part> parts) throws InvalidInventoryException {
        
        if(validateParts(parts, getPrice())){
            this.parts = parts;
        }

    }
    
    private boolean validateParts(ArrayList<Part> parts, double price) throws InvalidInventoryException{
        boolean isValid = true;
        
        if (parts.isEmpty()) {
            throw new InvalidInventoryException("No parts");
        } else if (price < 0) {
            throw new IllegalArgumentException("Price must not be negative");
        } else {
            double sum = 0.0;
            for (Part part : parts) {
                sum += part.getPrice();
            }

            if (price < sum) {
                throw new InvalidInventoryException("Price less than sum of parts");
            }
        }
        
        return isValid;
    }
    

    
    
    
  



  

  





    
    //Used for demonstration purposes. A more robust ID management system would be needed
    private int generateNextID() {
        return ++nextID;
    }
}
