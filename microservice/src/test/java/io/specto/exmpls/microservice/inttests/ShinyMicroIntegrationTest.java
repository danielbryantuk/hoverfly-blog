package io.specto.exmpls.microservice.inttests;

import io.specto.exmpls.microservice.Application;
import io.specto.exmpls.microservice.model.ComplexObject;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TestRule;
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

    public static final String SPEEDY_REQUEST_URI = "http://localhost:8090/speedyRequest";

    @ClassRule
    public static TestRule hoverflyRule = new HoverflyRule();

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void test() {
        ComplexObject[] complexObjects =
                restTemplate.getForObject(SPEEDY_REQUEST_URI, ComplexObject[].class);
        assertTrue(complexObjects.length == 1);
    }
}