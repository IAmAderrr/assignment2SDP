package families;
import abstractfactory.*;
public final class ColdStorageWarehouseFactory implements WarehouseFactory {
 public Scanner createScanner(){ return new ColdResistantScanner(); }
 public LabelPrinter createLabelPrinter(){ return new FreezerLabelPrinter(); }
 public Sorter createSorter(){ return new ColdStorageSorter(); }
 public String familyName(){ return "ColdStorage"; }
}
