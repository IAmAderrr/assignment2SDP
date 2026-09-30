package factorymethod;

public final class ExpressPackaging implements PackagingPlan {
    public String name() { return "Express"; }
    public double materialCost(double weightKg) { return 2.8 + weightKg * 0.35; }
    public String handlingInstruction() { return "Priority seal and express lane"; }
}
