package edu.wgu.sbro323;

import java.util.ArrayList;

public class Inventory {
	
	private ArrayList<Product> products;
	
	
	
	public void addProduct(Product product){
		products.add(product);
	}
	
	public boolean removeProduct(int num){
		return false;
	}
	
	public Product lookupProduct(int num){
		return new Product();
	}
	
	public void updateProduct(int num){
		
	}

}
