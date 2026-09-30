package families;
import abstractfactory.LabelPrinter;
public final class SecureLabelPrinter implements LabelPrinter {
 public String model(){ return "SecureLabelPrinter"; }
 public String printLabel(String id,String d){ return "PHARMA label " + id + " -> " + d + " (tamper-evident)"; }
}
