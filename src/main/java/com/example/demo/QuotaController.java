package com.example.demo;

import com.example.demo.model.Feature;
import com.example.demo.model.Quotas;
import com.example.demo.service.FeatureService;
import com.example.demo.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotas")
public class QuotaController {


    @Autowired
    QuotaService quotaService;


    @PostMapping
    public Quotas createQuota(@RequestBody Quotas quotas) {

        return quotaService.createQuota(quotas);
    }



}
