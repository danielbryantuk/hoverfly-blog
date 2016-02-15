package io.specto.exmpls.microservice.services;

import io.specto.exmpls.microservice.model.ComplexObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ComplexObjectService {

    @Autowired
    private RestTemplate restTemplate;

    public List<ComplexObject> getLatestComplexObjects() {
        ComplexObject[] objects =
                restTemplate.getForObject("http://localhost:8080/slowFragileRequest", ComplexObject[].class);
        // Process ComplexObjects into something else
        return Arrays.asList(objects);
    }
}
