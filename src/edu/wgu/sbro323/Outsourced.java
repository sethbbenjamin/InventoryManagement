package edu.wgu.sbro323;

public class Outsourced extends Part {

	private String companyName;
        
        Outsourced(String name, Double price, Integer instock, Integer min, Integer max, String companyName) throws InvalidInventoryException {
        super(name, price, instock, min, max);
        setCompanyName(companyName);
    }
        
	public String getCompanyName() {
		return companyName;
	}

	public final void setCompanyName(String companyName) {
	    if (companyName == null || companyName.isEmpty()) {
                throw new IllegalArgumentException("Company name must not be blank");
            }	
            this.companyName = companyName;
	}
}
