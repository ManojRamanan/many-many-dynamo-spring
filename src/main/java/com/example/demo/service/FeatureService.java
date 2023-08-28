package com.example.demo.service;

import com.example.demo.model.Feature;
import com.example.demo.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeatureService {

    @Autowired
    FeatureRepository featureRepository;

    public Feature createFeatures(Feature feature) {
        feature.setId(UUID.randomUUID().toString());
        featureRepository.createFeature(feature);
        return feature;
    }
}
