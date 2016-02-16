package io.specto.exmpls.microservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    @Value("${hoverfly.host:localhost}")
    private String hoverflyHost;

    @Value("${hoverfly.port:8500}")
    private int hoverflyPort;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Profile("TEST")
    public RestTemplate getHoverflyProxiedRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hoverflyHost, hoverflyPort));
        requestFactory.setProxy(proxy);
        return new RestTemplate(requestFactory);
    }

    @Bean
    @Profile("PROD")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
