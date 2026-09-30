package factorymethod;

/** Factory Method creator: subclasses decide which PackagingPlan is created. */
public abstract class PackagingCreator {
    protected abstract PackagingPlan createPackaging();

    /** Meaningful business logic that uses the product created by the factory method. */
    public PackagingQuote prepare(double weightKg) {
        if (weightKg <= 0) throw new IllegalArgumentException("Weight must be positive");
        PackagingPlan plan = createPackaging();
        double baseLabor = 2.25;
        double total = baseLabor + plan.materialCost(weightKg);
        return new PackagingQuote(plan.name(), plan.handlingInstruction(), Math.round(total * 100.0) / 100.0);
    }
}
