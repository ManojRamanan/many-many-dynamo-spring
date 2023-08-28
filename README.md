# Many to Many mapping using spring and dynamodb

Many-to-Many Mapping using DynamoDB and Spring Boot

This README provides an overview of implementing a many-to-many relationship using DynamoDB and Spring Boot, using the example entities "Features" and "Vouchers". In this scenario, a voucher can have multiple features, and a feature can be associated with multiple vouchers. We will cover the data modeling, setup, and interactions using Spring Boot and DynamoDB.


# Entities

    Features: Represents the features that vouchers can have.
    Vouchers: Represents the vouchers that can have multiple features.

# Data Modeling

    Composite Primary Key: Each entity has a composite primary key consisting of the VoucherId as the partition key (PK) and FeatureId or VoucherId as the sort key (SK), depending on the entity.
    Global Secondary Index (GSI): A GSI with FeatureId as the partition key and VoucherId as the sort key is used to efficiently query for vouchers based on their associated features.

# Project Setup

    Create a new Spring Boot project.
    Add the AWS SDK DynamoDB dependency to your pom.xml.

# Maven dependency

<dependency>
    <groupId>com.amazonaws</groupId>
    <artifactId>aws-java-sdk-dynamodb</artifactId>
</dependency>
