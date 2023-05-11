package com.example.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Configuration
public class AwsS3ClientConfig {

    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    @Bean
    public AmazonS3 s3Client() throws IOException {
        // read credential file
        InputStream in = AwsS3ClientConfig.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        // parse credentials
        JsonElement json = JsonParser.parseReader( new InputStreamReader(in) );
        AWSCredentials credentials = new BasicAWSCredentials(
                json.getAsJsonObject().get("AccessKeyID").getAsString(),
                json.getAsJsonObject().get("SecretAccessKey").getAsString()
        );
        // return S3 client
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }



}
