package autowireproblem;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.function.Supplier;

import static autowireproblem.LambdasHolder.create;
import static autowireproblem.LambdasHolder.createWithLambda;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class AutowireProblemTest {

    @BeforeClass
    public static void beforeClass() {
        assertEquals("4.2.1.RELEASE", SpringVersion.getVersion());

        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        assertTrue("Must run with asserts", inputArguments.contains("-ea"));
        assertTrue("Must run with spring-instrument agent", inputArguments.toString().matches(".*-javaagent.*spring-instrument-4.2.1.RELEASE.jar.*"));
    }

    @Test
    public void test() {
        createWithNewOperator();
        createWithAnonymousInnerClass();
        createWithLambda().test();
    }


    private void createWithNewOperator() {
        ConfigurableComponent a = new ConfigurableComponent();
        a.test();
        assertNotNull(a);
    }

    @SuppressWarnings("Convert2Lambda")
    private void createWithAnonymousInnerClass() {
        ConfigurableComponent a = create(new Supplier<ConfigurableComponent>() {
            @Override
            public ConfigurableComponent get() {
                ConfigurableComponent a1 = new ConfigurableComponent();
                a1.test();
                return a1;
            }
        });
        assertNotNull(a);
    }
}
