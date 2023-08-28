package com.example.demo.repository;

import com.example.demo.model.Quotas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;

@Repository
public class QuotaRepository {

    @Autowired
    DynamodbRepository dynamodbRepository;

    public Quotas createQuotas(Quotas quotas) {

        //Implement create voucher here

        //master_db
        Map<String, AttributeValue> quota = new HashMap<>();
        quota.put("pk", AttributeValue.builder().s(quotas.getQuotaId()).build());
        quota.put("sk", AttributeValue.builder().s(quotas.getQuotaName()).build());
        quota.put("productId", AttributeValue.builder().s(String.valueOf(quotas.getQuantity())).build());
        quota.put("featuresList", AttributeValue.builder().ss(quotas.getFeatureList()).build());

        // Specify the table name
        String tableName = "master_db"; // Replace with your table name

        // Create a PutItemRequest to insert the feature object into the table
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(tableName)
                .item(quota)
                .build();

        dynamodbRepository.getDdb().putItem(putItemRequest);

        return quotas;
    }

    public Map<String, AttributeValue> getQuota(String quotaId) {

        //Implement create voucher here

        //master_db
        Map<String, AttributeValue> quota = new HashMap<>();
        quota.put(":pk", AttributeValue.builder().s(quotaId).build());

        // Specify the table name
        String tableName = "master_db"; // Replace with your table name


        QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .keyConditionExpression("pk = :pk")
                .expressionAttributeValues(quota)
                .build();

        Map<String, AttributeValue> item = dynamodbRepository.getDdb().query(queryRequest).items().get(0);

        return item;

    }
}
