package org.patrick;

public interface Reportable {

    /**
     * Generates a system report.
     * @return the generated report
     */
    String generateReport(Library library);
}
