package families;
import abstractfactory.LabelPrinter;
public final class ThermalLabelPrinter implements LabelPrinter {
 public String model(){ return "ThermalLabelPrinter"; }
 public String printLabel(String id,String d){ return "ECOM label " + id + " -> " + d + " (thermal)"; }
}
