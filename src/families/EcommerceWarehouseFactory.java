package families;
import abstractfactory.*;
public final class EcommerceWarehouseFactory implements WarehouseFactory {
 public Scanner createScanner(){ return new FastBarcodeScanner(); }
 public LabelPrinter createLabelPrinter(){ return new ThermalLabelPrinter(); }
 public Sorter createSorter(){ return new HighSpeedSorter(); }
 public String familyName(){ return "Ecommerce"; }
}
