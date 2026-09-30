package families;
import abstractfactory.Scanner;
public final class TraceableScanner implements Scanner {
 public String model(){ return "TraceableScanner"; }
 public String scan(String id){ return "PHARMA scanned " + id + " with lot traceability"; }
}
