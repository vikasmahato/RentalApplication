package rental.web;

import rental.factory.QuickbooksBackendFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHomeController {
    @Autowired
    QuickbooksBackendFactory factory;

    @Autowired
    HomeController homeController;

    @Test
    public void testHome() {
        assertEquals("home", homeController.home());
    }
}
