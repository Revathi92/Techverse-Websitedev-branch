package com.dailycodebuffer.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfiguration {

	public static final String SERVICE_ENDPOINT = "dynamodb.us-east-2.amazonaws.com";
    public static final String REGION = "us-east-2";
    public static final String ACCESS_KEY = "AKIA3J3X3T2OHQ2NMKXO";
    public static final String SECRET_KEY = "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";
    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                        		SERVICE_ENDPOINT, REGION
                        )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                		ACCESS_KEY, SECRET_KEY
                                )
                        )
                )
                .build();
    }

}
