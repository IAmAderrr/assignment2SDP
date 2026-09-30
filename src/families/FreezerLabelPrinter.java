package families;
import abstractfactory.LabelPrinter;
public final class FreezerLabelPrinter implements LabelPrinter {
 public String model(){ return "FreezerLabelPrinter"; }
 public String printLabel(String id,String d){ return "COLD label " + id + " -> " + d + " (frost-resistant)"; }
}
