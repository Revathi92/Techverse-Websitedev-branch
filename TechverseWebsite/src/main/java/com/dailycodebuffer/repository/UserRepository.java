package com.dailycodebuffer.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.dailycodebuffer.entity.Meeting;
import com.dailycodebuffer.entity.Phone;
import com.dailycodebuffer.entity.User;

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
    
    public void save(Phone otp) {
    	dynamoDBMapper.batchSave(otp);
    }
    
    public Date getOTPDetialsForSms(String phoneNo, int otp) {
    	// call sql query below example like this w eneed to write some sql and get date from db
    	//@Query(value="SELECT CREATED_DATE FROM OTP_PHONE_DETAILS WHERE PHONE_NO =:phoneno AND OTP =:otp AND STATUS='NEW'",nativeQuery=true)
    	
    	return null;
    	
    	
    }
    
}
