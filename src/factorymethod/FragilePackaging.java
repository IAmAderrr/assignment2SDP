package factorymethod;

public final class FragilePackaging implements PackagingPlan {
    public String name() { return "Fragile"; }
    public double materialCost(double weightKg) { return 4.0 + weightKg * 0.55; }
    public String handlingInstruction() { return "Add cushioning and FRAGILE markers"; }
}
