package families;
import abstractfactory.Scanner;
public final class ColdResistantScanner implements Scanner {
 public String model(){ return "ColdResistantScanner"; }
 public String scan(String id){ return "COLD scanned " + id + " with glove-safe optics"; }
}
