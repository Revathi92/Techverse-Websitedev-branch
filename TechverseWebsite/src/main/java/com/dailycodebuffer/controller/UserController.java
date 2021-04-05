package com.dailycodebuffer.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dailycodebuffer.entity.User;

import com.dailycodebuffer.DynanoDbSpringBootDemoApplication;
import com.dailycodebuffer.entity.Meeting;
import com.dailycodebuffer.repository.UserRepository;


@RestController
@RequestMapping("/techversewebsite")


public class UserController  {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(DynanoDbSpringBootDemoApplication.class);
	

    private static final String MAIL_RECEPTENT = "pediredlanagarevathi@gmail.com";
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private JavaMailSender mailSenderObj;
	
	@GetMapping("/index")
	public ResponseEntity<String> index(){
		LOGGER.info("UserController - index() :: Start");
		String message = "Welcome to Broker API...!";
		LOGGER.info("UserController - index() :: End");
		return ResponseEntity.ok().body(message);		
	}
	

	
    @PostMapping("/createuser")
    public User saveEmployee(@RequestBody User user) {
    	 user.setStatus("1");
         userRepository.save(user);
         sendmail(user,user.getEmailId());
         return user;
    }

    private void sendmail(User user, String userEmailId) {
		// TODO Auto-generated method stub
    	
    	final String emailToRecipient = MAIL_RECEPTENT;
    	final String emailFromRecipient = userEmailId;
		final String emailSubject = "Enquiry Done ";

		final String emailMessage1 = "<html> <body> <p>Dear Sir/Madam,</p><p>You have succesfully Registered with our Services"
				+ "<br><br>"
				//+ "<table border='1' width='300px' style='text-align:center;font-size:20px;'><tr> <td colspan='2'>"
				+ "<tr><td>Name</td><td>" + user.getName() + "</td></tr><tr><td>Email</td><td>"
				+ user.getEmailId() + "</td></tr><tr><td>Message</td><td>" + user.getMessage()
				+ "</td></tr> </body></html>";

		mailSenderObj.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");

				mimeMsgHelperObj.setTo(emailToRecipient);
				mimeMsgHelperObj.setFrom("test@gmail.com");
	
				mimeMsgHelperObj.setText(emailMessage1, true);

				mimeMsgHelperObj.setSubject(emailSubject);

			}
		});
		
	}

	@GetMapping("/user/{id}")
    public User getEmployeeById(@PathVariable("id") String userId) {
		return userRepository.getUserById(userId);
    }
	
	@GetMapping("/user/{status}")
	public List<User> getBooks(@PathVariable("status") String status) {
		return (List<User>) userRepository.findAll(status);
	}


    @DeleteMapping("/user/{id}")
    public String deleteEmployee(@PathVariable("id") String userId) {
        return  userRepository.delete(userId);
    }

    @PutMapping("/user/{id}")
    public String updateEmployee(@PathVariable("id") String userId, @RequestBody User user) {
        return userRepository.update(userId,user);
    }
    
    //sent mail for meeting and save data
    @RequestMapping(value="/customer/save", method ={RequestMethod.POST,RequestMethod.GET})
    public ResponseEntity<String> saveMeetingandsendEmail(@RequestBody Meeting meeting) {
    	System.out.println("enter controller");
    	userRepository.save(meeting);
         sendmailformeeting(meeting,meeting.getEmail());
        // return "User Profile entry  deleted with number : ", HttpStatus.OK;
         return new ResponseEntity<String>("Meeting details saved..",HttpStatus.OK);
	}
        // return new ResponseEntity<Meeting>("Your number is Verified",HttpStatus.OK);
    
    
    private void sendmailformeeting(Meeting meeting, String email) {
		// TODO Auto-generated method stub
    	final String emailToRecipient = MAIL_RECEPTENT;
    	final String emailFromRecipient = email;
		final String emailSubject = "Meeting Schedule Notification ";

		final String emailMessage1 = "<html> <body> <p>Dear Sir/Madam,</p><p>Meeting has been schedule by client as per below details"
				+ "<br><br>"
				//+ "<table border='1' width='300px' style='text-align:center;font-size:20px;'><tr> <td colspan='2'>"
				+ "<tr><td>Name</td><td>" + meeting.getFullname() + "</td></tr><tr><td>Email</td><td>"
				+ meeting.getEmail() + "</td></tr><tr><td>Message</td><td>" + meeting.getDateandtime() + "</td></tr><tr><td>Message</td><td>" 
				+ meeting.getTitle()
				+ "</td></tr> </body></html>";
		
		mailSenderObj.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");

				mimeMsgHelperObj.setTo(emailToRecipient);
				mimeMsgHelperObj.setFrom("test@gmail.com");
	
				mimeMsgHelperObj.setText(emailMessage1, true);

				mimeMsgHelperObj.setSubject(emailSubject);

			}
		});
		
	}
    	
    	
    	
    	
		
	}


