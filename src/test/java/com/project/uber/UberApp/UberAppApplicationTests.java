package com.project.uber.UberApp;

import com.project.uber.UberApp.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberAppApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;
	@Test
	void contextLoads() {
		emailSenderService.sendEmail(
				"maazjahangir07@gmail.com",
				"This is the testing email",
				"Hello from this side! Keep coding");
	}
	@Test
	void sendEmailMultiple(){
		String emails[] = {
				"abhishekanand1185@gmail.com",
				"skmallick3011@gmail.com",
				"maazjahangir07@gmail.com",
				"chengvegita777@gmail.com"
		};
		emailSenderService.sendEmail(emails,"Hello from the application Demo","Abhishek this side, Keep Coding !!");
	}

}
