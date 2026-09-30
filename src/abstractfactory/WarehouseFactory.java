package abstractfactory;
public interface WarehouseFactory {
    Scanner createScanner();
    LabelPrinter createLabelPrinter();
    Sorter createSorter();
    String familyName();
}
