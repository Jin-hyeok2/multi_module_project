package com.example.component;

import com.example.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootMemberMailer {

    private final JavaMailSender javaMailSender;

    public void sendMail(String to, String title, String text) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(title);
        message.setText(text);
        try {
            javaMailSender.send(message);

        } catch (Exception e) {
            throw MemberException.failMailSend(to);
        }
    }
}
