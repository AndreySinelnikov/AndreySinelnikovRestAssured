package speller;

import org.testng.annotations.BeforeClass;
import service.RestCheckTextService;

public class BaseTest {
    RestCheckTextService restCheckTextService;

    @BeforeClass
    public void setUp() {
        restCheckTextService = new RestCheckTextService().getInstance();
    }
}
