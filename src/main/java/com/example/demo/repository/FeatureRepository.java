package com.example.demo.repository;

import com.example.demo.model.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class FeatureRepository {

    @Autowired
    DynamodbRepository dynamodbRepository;




    public void createFeature(Feature feature) {

        //Implement create feature here
        Map<String, AttributeValue> featureItem = new HashMap<>();
        featureItem.put("pk", AttributeValue.builder().s(feature.getId()).build());
        featureItem.put("sk", AttributeValue.builder().s(feature.getId()).build());
        featureItem.put("name", AttributeValue.builder().s(feature.getName()).build());
        featureItem.put("description", AttributeValue.builder().s(feature.getDescription()).build());

        // Specify the table name
        String tableName = "master_db"; // Replace with your table name

        // Create a PutItemRequest to insert the feature object into the table
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(tableName)
                .item(featureItem)
                .build();

        // Insert the feature object into the table
        dynamodbRepository.getDdb().putItem(putItemRequest);

        System.out.println("Feature object inserted successfully.");


    }


    public Map<String, AttributeValue> getFeatureById(String featureId) {

        //Implement create voucher here

        //master_db
        Map<String, AttributeValue> quota = new HashMap<>();
        quota.put("pk", AttributeValue.builder().s(featureId).build());
        quota.put("sk", AttributeValue.builder().s(featureId).build());
        // Specify the table name
        String tableName = "master_db"; // Replace with your table name

        GetItemRequest getItemRequest = GetItemRequest.builder()
                .tableName(tableName)
                .key(quota)
                .build();

        Map<String, AttributeValue> item = dynamodbRepository.getDdb().getItem(getItemRequest).item();

        return item;

    }




}
