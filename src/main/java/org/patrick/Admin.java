package org.patrick;

public class Admin extends User implements Reportable {

    public Admin(String name) {
        super(name);
    }

    @Override
    public String toCSV() {
        return id + "," + name + ",Admin";
    }

    @Override
    public int getBorrowLimit() {
        return 0;
    }

    @Override
    public boolean canBorrow(Item item) {
        return false;
    }

    /**
     * Placeholder for report feature (used later with Reportable interface)
     * @return placeholder
     */
    @Override
    public String generateReport() {
        //TODO: implement in later deliverable
        return "Report generation not implemented yet";
    }

    public void backupData() {
        //TODO: implement in later deliverable
    }
}
