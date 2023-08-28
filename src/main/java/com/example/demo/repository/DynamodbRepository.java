package com.example.demo.repository;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Component
public class DynamodbRepository {

    private DynamoDbClient ddb;

    DynamodbRepository(){
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create("n3hum", "a8fnru");
        Region region = Region.US_EAST_1;
        ddb = DynamoDbClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();

    }

    public DynamoDbClient getDdb() {
        return ddb;
    }
}
