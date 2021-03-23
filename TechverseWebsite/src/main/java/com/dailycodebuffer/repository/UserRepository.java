package com.dailycodebuffer.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.dailycodebuffer.entity.Meeting;
import com.dailycodebuffer.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;


    public User save(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public User getUserById(String userId) {
        return dynamoDBMapper.load(User.class, userId);
    }

    public String delete(String userId) {
        User emp = dynamoDBMapper.load(User.class, userId);
        dynamoDBMapper.delete(emp);
        return "Employee Deleted!";
    }

    public String update(String userId, User user) {
        dynamoDBMapper.save(user,
                new DynamoDBSaveExpression()
        .withExpectedEntry("employeeId",
                new ExpectedAttributeValue(
                        new AttributeValue().withS(userId)
                )));
        return userId;
    }

	public List<User> findAll(String status) {
		return (List<User>) dynamoDBMapper.load(User.class, status);
	}
	
   //  save meeting details
    public Meeting save(Meeting meeting) {
        dynamoDBMapper.save(meeting);
        return meeting;
    }
    
}
