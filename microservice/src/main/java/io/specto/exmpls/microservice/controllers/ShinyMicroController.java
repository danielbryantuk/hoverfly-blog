package io.specto.exmpls.microservice.controllers;

import io.specto.exmpls.microservice.model.ComplexObject;
import io.specto.exmpls.microservice.services.ComplexObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShinyMicroController {

    @Autowired
    private ComplexObjectService complexObjectService;

    @RequestMapping("/speedyRequest")
    @ResponseBody
    public List<ComplexObject> speedyRequest(@RequestParam(value = "origin", required = false) final String origin,
                                             @RequestParam(value = "destination", required = false) final String destination) {
        return complexObjectService.getLatestComplexObjects(origin, destination);
    }

}