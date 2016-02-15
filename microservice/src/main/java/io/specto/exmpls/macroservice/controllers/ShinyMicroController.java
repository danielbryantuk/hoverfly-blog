package io.specto.exmpls.macroservice.controllers;

import io.specto.exmpls.macroservice.model.ComplexObject;
import io.specto.exmpls.macroservice.services.ComplexObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShinyMicroController {

    @Autowired
    private ComplexObjectService complexObjectService;

    @RequestMapping("/speedyRequest")
    @ResponseBody
    public List<ComplexObject> speedyRequest() {
        return complexObjectService.getLatestComplexObjects();
    }

}