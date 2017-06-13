package autowireproblem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class ConfigurableComponent {

    @Autowired
    private transient Component component;

    public void test() {
        assert component != null : "SpringComponent has not been autowired!";
    }
}
