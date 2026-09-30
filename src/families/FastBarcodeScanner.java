package families;
import abstractfactory.Scanner;
public final class FastBarcodeScanner implements Scanner {
 public String model(){ return "FastBarcodeScanner"; }
 public String scan(String id){ return "ECOM scanned " + id + " at high speed"; }
}
