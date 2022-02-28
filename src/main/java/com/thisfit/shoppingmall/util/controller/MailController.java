package com.thisfit.shoppingmall.util.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping("/sendMail")
public class MailController {
	
	private static final Logger log = LoggerFactory.getLogger(MailController.class);
	
	// Java에서 제공하는 mail 객체 주입
	private final JavaMailSender mailSender;
	
	// 이메일 전송
	@ResponseBody
	@PostMapping
	public String sendMailPost(String email) throws Exception{
		log.info("이메일 전송 email : " + email);
		
		// 인증번호(난수) 생성
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		
		String from = "idnm0513@gmail.com";
		String to = email;
        String title = "ThisFit 인증번호 메일입니다.";
        String content = "인증번호는 " + checkNum + " 입니다.";
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
            // true는 멀티파트 메세지를 사용하겠다는 의미
            
            /*
             * 단순한 텍스트만 사용한다면 아래의 코드도 사용 가능 
             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
             */
            
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(title);
            mailHelper.setText(content, true);
            // true는 html을 사용하겠다는 의미
            
            /*
             * 단순한 텍스트만 사용한다면 아래의 코드도 사용 가능
             * mailHelper.setText(content);
             */
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        // 생성하고 메일로 보낸 인증번호(난수)를 String으로 리턴
        return Integer.toString(checkNum);
    }
	
}
