package io.specto.exmpls.microservice.inttests;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class AbstractHoverflyIntegrationTest {

    private static final String HOVERFLY_LOCATION = "/Users/danielbryant/Documents/dev/daniel-bryant-uk/hoverfly-blog/microservice/";

    private static Process hoverflyProcess;

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
}
