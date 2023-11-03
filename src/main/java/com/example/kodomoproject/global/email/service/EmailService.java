package com.example.kodomoproject.global.email.service;

import com.example.kodomoproject.domain.auth.controller.dto.response.EmailVerifyResponse;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.entity.UserRole;
import com.example.kodomoproject.domain.user.repository.UserRepository;
import com.example.kodomoproject.domain.user.service.facade.UserFacade;
import com.example.kodomoproject.global.email.controller.dto.EmailRequest;
import com.example.kodomoproject.global.email.exception.AlreadyAuthenticatedUserException;
import com.example.kodomoproject.global.email.exception.MailSendException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final JavaMailSender mailSender;
    private final AuthCodeDao authCodeDao;

    @Value("${AdminMail.id}")
    private String adminMail;

    private static final Random rnd = new Random();
    private static final String ENCODING = "UTF-8";

    private static final String MISMATCH_MESSAGE = "인증코드가 일치하지 않습니다.";
    private static final String VERIFIED_MESSAGE = "인증코드가 확인되었습니다.";

    private MimeMessage sendEmailForAuth(String to) throws MessagingException, UnsupportedEncodingException {
        String authKey = createKey();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, ENCODING);

        helper.setTo(to);
        helper.setSubject("이메일 인증 번호입니다.");

        String htmlBody = getEmailTemplateForAuth(authKey);

        helper.setText(htmlBody, true);
        helper.setFrom(new InternetAddress(adminMail, "email-verify"));

        authCodeDao.saveAuthCode(to, authKey);

        return message;
    }

    private String getEmailTemplateForAuth(String authKey) {
        return "<div style='margin: 10px; background-color: #f5f5f5; padding: 20px; border-radius: 10px;'>"
                + "<p style='font-size: 16px; color: #333;'><b><span style='color: #007bff;'>D</span><span style='color: #ffcc00;'>S</span><span style='color: #ff0000;'>M</span></b> 이메일 인증 코드 :</p>"
                + "<p style='font-size: 24px; font-weight: bold; color: #007bff; letter-spacing: 3px;'>" + authKey + "</p>"
                + "<p style='font-size: 14;font-style: italic; color: #999;'>인증 코드는 3시간 동안 유효합니다.</p>"
                + "</div>";
    }

    private MimeMessage sendEmailForResetPassword(String to, String link) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, ENCODING);

        helper.setTo(to);
        helper.setSubject("비밀번호 지원 - 대마고 중고거래 서비스");

        String htmlBody = getEmailTemplateForResetPassword(link);

        helper.setText(htmlBody, true);
        helper.setFrom(new InternetAddress(adminMail, "password-reset"));

        return message;
    }

    private String getEmailTemplateForResetPassword(String link) {
        return "<div style='margin:20px;'>"
                + "<p><b>대팔이</b> - 대마고 중고거래 서비스에서 알려드립니다</p><br>"
                + "<div>이 이메일 주소와 연결된 계정의 비밀번호 재설정 요청을 받았습니다.</div>"
                + "<p style='font-size:100%;'>본인 확인 후 아래 링크를 클릭하여 비밀번호를 재설정하십시오.</p>"
                + "<div>"
                + "<div style='font-size:200%'>"
                + "<a href='" + link + "' style='color: orange; text-decoration: none; font-weight: lighter;'>비밀번호 재설정</a>"
                + "</div>"
                + "<br>"
                + "</div>"
                + "</div>";
    }

    private MimeMessage sendMailForSuccessPasswordReset(String to) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, ENCODING);

        helper.setTo(to);
        helper.setSubject("비밀번호 업데이트 완료");

        String htmlBody = getEmailTemplateForSuccessPasswordReset();

        helper.setText(htmlBody, true);
        try {
            helper.setFrom(new InternetAddress(adminMail, "account-update"));
        } catch (UnsupportedEncodingException e) {
            throw MailSendException.EXCEPTION;
        }
        return message;
    }

    private String getEmailTemplateForSuccessPasswordReset() {
        return "<div style='margin:20px;'>"
                + "<p><b>대팔이</b> - 대마고 중고거래 서비스에서 알려드립니다</p><br>"
                + "<div>이 이메일 주소와 연결된 계정의 비밀번호를 업데이트 했습니다.</div>"
                + "<p style='font-size:100%;'>본인이 아닐 시 문의</p>"
                + "</div>";
    }

    @Async
    public void sendSuccessPasswordReset(String to) throws MessagingException {
            MimeMessage message = sendMailForSuccessPasswordReset(to);
            sendSimpleMessage(message);
    }

    @Async
    public void sendAuthCode(EmailRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getEmail();
        User user = userFacade.getUserByEmail(email);

        if (user.getRole() == UserRole.USER) {
            throw AlreadyAuthenticatedUserException.EXCEPTION;
        }

        MimeMessage message = sendEmailForAuth(user.getEmail());

        sendSimpleMessage(message);
    }

    public void sendPasswordReset(String to, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = sendEmailForResetPassword(to, link);

        sendSimpleMessage(message);
    }
    private void sendSimpleMessage(MimeMessage message) {
        try{
            mailSender.send(message);
        }catch(MailException e){
            e.printStackTrace();
            throw MailSendException.EXCEPTION;
        }
    }

    private String createKey() {
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            key.append(rnd.nextInt(10));
        }

        return key.toString();
    }

    public EmailVerifyResponse verifyEmail(String authCode, EmailRequest request) {
        String email = request.getEmail();
        if (isVerify(authCode, email)) {
            authCodeDao.deleteAuthCode(email);
            User user = userFacade.getUserByEmail(email);
            user.addRole(UserRole.USER);
            userRepository.save(user);
            return createResponse(VERIFIED_MESSAGE, true);
        } else {
            return createResponse(MISMATCH_MESSAGE, false);
        }
    }

    private EmailVerifyResponse createResponse(String message, Boolean type) {
        return EmailVerifyResponse.builder()
                .message(message)
                .isSuccess(type)
                .build();
    }

    private boolean isVerify(String authCode, String email) {
        return (authCodeDao.hasKey(email) &&
                authCodeDao.getAuthCode(email)
                        .equals(authCode));
    }

    public void reissue(EmailRequest request) {
        User user = userFacade.getUserByEmail(request.getEmail());
        String email = user.getEmail();
        if (authCodeDao.hasKey(email)) {
            authCodeDao.deleteAuthCode(email);
        }
        try {
            sendAuthCode(request);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw MailSendException.EXCEPTION;
        }
    }

}
