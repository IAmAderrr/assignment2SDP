package factorymethod;
public final class FragilePackagingCreator extends PackagingCreator {
    protected PackagingPlan createPackaging() { return new FragilePackaging(); }
}
