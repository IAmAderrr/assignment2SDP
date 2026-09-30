package families;
import abstractfactory.*;
public final class HeavyCargoWarehouseFactory implements WarehouseFactory {
 public Scanner createScanner(){ return new RuggedScanner(); }
 public LabelPrinter createLabelPrinter(){ return new IndustrialLabelPrinter(); }
 public Sorter createSorter(){ return new HeavyDutySorter(); }
 public String familyName(){ return "HeavyCargo"; }
}
