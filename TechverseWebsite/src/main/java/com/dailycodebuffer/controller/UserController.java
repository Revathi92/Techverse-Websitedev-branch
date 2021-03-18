package com.dailycodebuffer.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.dailycodebuffer.entity.User;
import com.dailycodebuffer.repository.UserRepository;


@RestController


public class UserController  {

    private static final String MAIL_RECEPTENT = "pediredlanagarevathi@gmail.com";
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private JavaMailSender mailSenderObj;
	
	@CrossOrigin(origins = "http://localhost:4200")

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
}
