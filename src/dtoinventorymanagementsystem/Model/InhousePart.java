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
public class InhousePart extends Part {
    private int machineID;

    public InhousePart() {
        super();
    }

    public InhousePart(Part part) {
        super(part);
        
        if(part instanceof InhousePart)
            this.machineID = ((InhousePart) part).getMachineID();
    }
    
    public InhousePart(String name, double price, int inStock, int min, int max, int machineId) {
        super(name, price, inStock, min, max);
        setMachineID(machineId);
    }

    /**
     * @return the machineID
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * @param machineID the machineID to set
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
