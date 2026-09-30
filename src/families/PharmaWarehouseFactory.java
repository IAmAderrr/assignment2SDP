package families;
import abstractfactory.*;
public final class PharmaWarehouseFactory implements WarehouseFactory {
 public Scanner createScanner(){ return new TraceableScanner(); }
 public LabelPrinter createLabelPrinter(){ return new SecureLabelPrinter(); }
 public Sorter createSorter(){ return new ValidatedSorter(); }
 public String familyName(){ return "Pharma"; }
}
