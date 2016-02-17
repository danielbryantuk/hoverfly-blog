package io.specto.exmpls.macroservice.controllers;

import io.specto.exmpls.macroservice.model.ComplexObject;
import io.specto.exmpls.macroservice.services.ComplexObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ComplexController {

    @Autowired
    private ComplexObjectService complexObjectService;

    @RequestMapping("/slowFragileRequest")
    @ResponseBody
    public List<ComplexObject> slowFragileRequest(@RequestParam(value = "origin", required = false) final String origin,
                                                  @RequestParam(value = "destination", required = false) final String destination) {
        //todo - note that the request params are just for show, and are ignored in this example
        return complexObjectService.getLatestComplexObjects();
    }
}