package families;
import abstractfactory.LabelPrinter;
public final class IndustrialLabelPrinter implements LabelPrinter {
 public String model(){ return "IndustrialLabelPrinter"; }
 public String printLabel(String id,String d){ return "HEAVY label " + id + " -> " + d + " (industrial adhesive)"; }
}
