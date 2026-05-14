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
    public String generateReport(Library library) {
        String report = "Library report:\n\n";
        report += "Borrowed Items:\n";
        for (Item item : library.getItems()) {
            if (item.getStatus() == Item.Status.BORROWED) {
                report += item.toCSV() + "\n";
            }
        }

        report += "\nIn store Items\n";
        for (Item item : library.getItems()) {
            if (item.getStatus() == Item.Status.IN_STORE) {
                report += item.toCSV() + "\n";
            }
        }

        report += "\nLost Items:\n";
        for (Item item : library.getItems()) {
            if (item.getStatus() == Item.Status.LOST) {
                report += item.toCSV() + "\n";
            }
        }
        return report;
    }

    /**
     * Backs up all users and items from the library into CSV files.
     * @param library the library containing users and items to be saved
     */
    public void backupData(Library library) {
        library.backupUsersCSV("");
        library.backupItemsCSV("");
    }
}
