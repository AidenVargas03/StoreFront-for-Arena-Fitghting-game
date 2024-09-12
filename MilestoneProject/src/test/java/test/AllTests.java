package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SalableProductTest.class,
        InventoryManagerTest.class
})
public class AllTests {
    // This class remains empty, it is used only as a holder for the above annotations
}
