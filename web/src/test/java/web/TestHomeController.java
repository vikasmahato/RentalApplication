package web;

import factory.OAuth2PlatformClientFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rental.web.HomeController;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHomeController {
    @Autowired
    OAuth2PlatformClientFactory factory;

    @Autowired
    HomeController homeController;

    @Test
    public void testHome() {
        assertEquals("home", homeController.home());
    }
}
