package factorymethod;

public interface PackagingPlan {
    String name();
    double materialCost(double weightKg);
    String handlingInstruction();
}
