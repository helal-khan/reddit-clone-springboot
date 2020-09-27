package com.redditclone.service;

//import com.programming.techie.springredditclone.exception.SpringRedditException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.redditclone.exception.RedditCloneException;
import com.redditclone.model.NotificationEmail;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
	
	private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;
    
    @Async
    public void sendMail(NotificationEmail notificationEmail) {
    	
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("mhelalk505@gmail.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()), true);
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            throw new RedditCloneException("Exception occurred when sending mail to " + notificationEmail.getRecipient());
        }
    }

}
