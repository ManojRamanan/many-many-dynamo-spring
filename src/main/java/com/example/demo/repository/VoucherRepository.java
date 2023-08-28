package com.example.demo.repository;

import com.example.demo.model.Quotas;
import com.example.demo.model.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VoucherRepository {

    @Autowired
    DynamodbRepository dynamodbRepository;

    @Autowired
    FeatureRepository featureRepository;

    public Voucher createVoucher(Voucher voucher) {

        //Implement create voucher here

        //master_db
        Map<String, AttributeValue> voucherPayload = new HashMap<>();
        voucherPayload.put("pk", AttributeValue.builder().s(voucher.getId()).build());
        voucherPayload.put("sk", AttributeValue.builder().s(voucher.getId()).build());
        voucherPayload.put("name", AttributeValue.builder().s(voucher.getName()).build());
        voucherPayload.put("quotaId", AttributeValue.builder().s(voucher.getQuotaId()).build());
        voucherPayload.put("userId", AttributeValue.builder().s(voucher.getUserId()).build());
        // Specify the table name
        String tableName = "master_db"; // Replace with your table name

        // Create a PutItemRequest to insert the feature object into the table
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName(tableName)
                .item(voucherPayload)
                .build();

        dynamodbRepository.getDdb().putItem(putItemRequest);

        return voucher;
    }


    public Voucher voucherToFeatureMapper(Voucher voucher, Map<String, AttributeValue> quota) {

        //Implement create voucher here

        //master_db

        List<WriteRequest> writeRequestList = new ArrayList<>();
        List<String> featureList = quota.get("featuresList").ss();
        for (String featureId : featureList) {
            Map<String, AttributeValue> featureById = featureRepository.getFeatureById(featureId);
            Map<String, AttributeValue> voucherPayload = new HashMap<>();
            voucherPayload.put("pk", AttributeValue.builder().s(voucher.getId()).build());
            voucherPayload.put("sk", AttributeValue.builder().s(featureId).build());
            voucherPayload.put("name", AttributeValue.builder().s(featureById.get("name").s()).build());
            voucherPayload.put("description", AttributeValue.builder().s(featureById.get("description").s()).build());
            WriteRequest writeRequest = WriteRequest.builder()
                    .putRequest(PutRequest.builder()
                            .item(voucherPayload)
                            .build())
                    .build();

            writeRequestList.add(writeRequest);
        }


        // Specify the table name
        String tableName = "master_db"; // Replace with your table name

        // Create a PutItemRequest to insert the feature object into the table

        Map<String, List<WriteRequest>> requestItems = new HashMap<>();
        requestItems.put(tableName, writeRequestList);
        BatchWriteItemRequest batchWriteItemRequest = BatchWriteItemRequest.builder()
                .requestItems(requestItems)
                .build();

        dynamodbRepository.getDdb().batchWriteItem(batchWriteItemRequest);

        return voucher;
    }





}
