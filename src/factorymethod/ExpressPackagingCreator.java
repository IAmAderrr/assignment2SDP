package factorymethod;
public final class ExpressPackagingCreator extends PackagingCreator {
    protected PackagingPlan createPackaging() { return new ExpressPackaging(); }
}
