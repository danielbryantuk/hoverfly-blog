package io.specto.exmpls.microservice.inttests;

import org.junit.rules.ExternalResource;

public class HoverflyRule extends ExternalResource {

    private static final String HOVERFLY_LOCATION = "../../hoverfly-blog/microservice/";

    private static Process hoverflyProcess;

    @Override
    protected void before() throws Throwable {
        ProcessBuilder builder = new ProcessBuilder()
                .inheritIO()
                .command(HOVERFLY_LOCATION + "hoverfly");
        hoverflyProcess = builder.start();
    }

    @Override
    protected void after() {
        hoverflyProcess.destroy();
    }
}
