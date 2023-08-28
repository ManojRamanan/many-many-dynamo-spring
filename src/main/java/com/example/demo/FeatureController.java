package com.example.demo;

import com.example.demo.model.Feature;
import com.example.demo.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/features")
public class FeatureController {

    @Autowired
    FeatureService featureService;



    @PostMapping
    public Feature createFeatures(@RequestBody Feature feature) {

        return featureService.createFeatures(feature);
    }

}
