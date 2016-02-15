package io.specto.exmpls.microservice.inttests;

import io.specto.exmpls.microservice.Application;
import io.specto.exmpls.microservice.model.ComplexObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "TEST")
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class ShinyMicroIntegrationTest {

    private static final String HOVERFLY_LOCATION = "/Users/danielbryant/Documents/dev/daniel-bryant-uk/hoverfly-blog/microservice/";
    private static Process hoverflyProcess;

    private RestTemplate restTemplate = new RestTemplate();

    @BeforeClass
    public static void setup() throws Exception {
        ProcessBuilder builder = new ProcessBuilder()
                .inheritIO()
                .command(HOVERFLY_LOCATION + "hoverfly");
        hoverflyProcess = builder.start();
    }

    @AfterClass
    public static void teardown() throws Exception {
        hoverflyProcess.destroy();
    }

    @Test
    public void test() {
        ComplexObject[] complexObjects =
                restTemplate.getForObject("http://localhost:8090/speedyRequest", ComplexObject[].class);
        assertTrue(complexObjects.length == 1);
    }

}