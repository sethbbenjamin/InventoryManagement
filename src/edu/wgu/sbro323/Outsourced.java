package edu.wgu.sbro323;

public class Outsourced extends Part {

	private String companyName;

        Outsourced(){
            
        }
        
        Outsourced(String name, Double price, Integer instock, Integer min, Integer max, String companyName) {
        super(name, price, instock, min, max);
        this.companyName = companyName;
    }
        
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
