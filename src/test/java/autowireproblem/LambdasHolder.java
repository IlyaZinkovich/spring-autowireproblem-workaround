package autowireproblem;

import java.util.function.Supplier;

public class LambdasHolder {

    public static ConfigurableComponent createWithLambda() {
        return create(() -> {
            ConfigurableComponent a1 = new ConfigurableComponent();
            a1.test();
            return a1;
        });
    }

    public static <T> T create(Supplier<T> supplier) {
        return supplier.get();
    }
}
