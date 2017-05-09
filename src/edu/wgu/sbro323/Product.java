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
    private final IntegerProperty productID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty instock;
    private final IntegerProperty min;
    private final IntegerProperty max;
    
    private static int nextID = 200;


    Product() {
        name = new SimpleStringProperty();
        productID = new SimpleIntegerProperty(generateNextID());
        price = new SimpleDoubleProperty();
        instock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
        parts = new ArrayList<>();
    }
    
    Product(String name, Double price, Integer instock, Integer min, Integer max, ArrayList<Part> parts) {
        this.name = new SimpleStringProperty(name);
        this.productID = new SimpleIntegerProperty(generateNextID());
        this.price = new SimpleDoubleProperty(price);
        this.instock = new SimpleIntegerProperty(instock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.parts = parts;
    }

    //Used for demonstration purposes. A more robust ID management system would be needed
    private int generateNextID() {
        return ++nextID;
    }
    
    public ArrayList<Part> getParts() {
        return parts;
    }
    public void setParts(ArrayList<Part> parts) {
        this.parts = parts;
    }
    
    public void addPart(Part part){
        this.parts.add(part);
    }
    
    public void removePart(int id){
        this.parts.remove(id);
    }
    
    public Part lookupPart(int id){
        return this.parts.get(id);
    }
    
    public void updatePart(Part part){
        int index = this.parts.indexOf(part);
        this.parts.set(index, part);
    }

    public int getProductID() {
        return productID.get();
    }
    public void setProductID(int productID) {
        this.productID.set(productID);
    }
    public IntegerProperty productIDProperty() {
        return productID;
    }

    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        return name;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
    public DoubleProperty priceProperty() {
        return price;
    }

    public int getInstock() {
        return instock.get();
    }

    public void setInstock(int instock) {
        this.instock.set(instock);
    }
    public IntegerProperty instockProperty() {
        return instock;
    }

    public int getMin() {
        return min.get();
    }

    public void setMin(int min) {
        this.min.set(min);
    }
    public IntegerProperty minProperty() {
        return min;
    }

    public int getMax() {
        return max.get();
    }

    public void setMax(int max) {
        this.max.set(max);
    }
    public IntegerProperty maxProperty() {
        return max;
    }
}
