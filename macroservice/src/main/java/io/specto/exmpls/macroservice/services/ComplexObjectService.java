package io.specto.exmpls.macroservice.services;

import io.specto.exmpls.macroservice.model.ComplexObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComplexObjectService {

    private Map<Integer, ComplexObject> fakeRepo;

    public ComplexObjectService() {
        fakeRepo = new HashMap<>();
        fakeRepo.put(1, new ComplexObject("1", "largeObject", Arrays.asList("one", "two")));
        fakeRepo.put(2, new ComplexObject("2", "hugeObject", Arrays.asList("cat", "dog", "hamster")));
    }

    public List<ComplexObject> getLatestComplexObjects() {
        return new ArrayList<>(Collections.singletonList(fakeRepo.get(1)));
    }
}
