package factorymethod;

public final class StandardPackaging implements PackagingPlan {
    public String name() { return "Standard"; }
    public double materialCost(double weightKg) { return 1.5 + weightKg * 0.20; }
    public String handlingInstruction() { return "Normal handling"; }
}
