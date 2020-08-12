/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtoinventorymanagementsystem.Model;

/**
 *
 * @author Daniel
 */
public class OutsourcedPart extends Part {
    private String companyName = "";

    public OutsourcedPart() {
    }
    
    public OutsourcedPart(Part part) {
        super(part);
        
        if(part instanceof OutsourcedPart)
            this.companyName = ((OutsourcedPart) part).getCompanyName();
    }

    public OutsourcedPart(String name, double price, int inStock, int min, int max, String companyName) {
        super(name, price, inStock, min, max);
        this.companyName = companyName;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        if(companyName == null)
            companyName = "";
        this.companyName = companyName;
    }
}
