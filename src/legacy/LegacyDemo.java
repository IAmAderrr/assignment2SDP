package legacy;

/**
 * Part A snapshot: intentionally coupled code kept to demonstrate the problems
 * that factories solve. The production application does not use this class.
 */
public final class LegacyDemo {
    public String buildSystem(String environment) {
        String scanner;
        String printer;
        String sorter;

        if ("ECOMMERCE".equalsIgnoreCase(environment)) {
            scanner = "FastBarcodeScanner";
            printer = "ThermalLabelPrinter";
            sorter = "HighSpeedSorter";
        } else if ("COLD".equalsIgnoreCase(environment)) {
            scanner = "ColdResistantScanner";
            printer = "FreezerLabelPrinter";
            sorter = "ColdStorageSorter";
        } else if ("HEAVY".equalsIgnoreCase(environment)) {
            scanner = "RuggedScanner";
            printer = "IndustrialLabelPrinter";
            sorter = "HeavyDutySorter";
        } else {
            throw new IllegalArgumentException("Unknown environment: " + environment);
        }

        return scanner + " + " + printer + " + " + sorter;
    }
}
