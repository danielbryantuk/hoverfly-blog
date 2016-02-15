package io.specto.exmpls.microservice.model;

import java.util.ArrayList;
import java.util.List;

public class ComplexObject {
    private String id;
    private String name;
    private List<String> mappings = new ArrayList<>();

    public ComplexObject() {
    }

    public ComplexObject(String id, String name, List<String> mappings) {
        this.id = id;
        this.name = name;
        this.mappings = mappings;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getMappings() {
        return mappings;
    }
}
