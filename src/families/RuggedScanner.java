package families;
import abstractfactory.Scanner;
public final class RuggedScanner implements Scanner {
 public String model(){ return "RuggedScanner"; }
 public String scan(String id){ return "HEAVY scanned " + id + " with rugged long-range reader"; }
}
