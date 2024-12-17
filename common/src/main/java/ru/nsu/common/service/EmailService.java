package ru.nsu.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.function.UnaryOperator;

import static ru.nsu.common.constants.Parameters.*;
import static ru.nsu.common.constants.Path.*;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${application.base-url}")
    private String baseUrl;

    @Value("${application.email.from}")
    private String from;

    public void sendConfirmationEmail(String recipient, String token) {
        new ConfirmationEmailSenderDelegate(recipient, token).sendEmail();
    }

    public void sendRecoveryEmail(String recipient, String token) {
        new RecoveryEmailSenderDelegate(recipient, token).sendEmail();
    }

    public void sendEmailChangedEmail(String recipient, String newEmailAddress) {
        new ChangeEmailSenderDelegate(recipient, newEmailAddress).sendEmail();
        new ChangeEmailSenderDelegate(newEmailAddress, newEmailAddress).sendEmail();
    }

    public void sendPasswordChangedEmail(String recipient) {
        new ChangePasswordSenderDelegate(recipient).sendEmail();
    }



    private class ConfirmationEmailSenderDelegate {

        private static final String CONFIRMATION_SUBJECT = "Link for account confirmation";

        private static final UnaryOperator<String> CONFIRMATION_CONTENT =
            link -> String.format(
                "Hello!\n\nYour confirmation link is %s\n\nPlease, follow the link as soon as possible.", link
            );

        private final String recipient;

        private final String token;

        public ConfirmationEmailSenderDelegate(String recipient, String token) {
            this.recipient = recipient;
            this.token = token;
        }

        void sendEmail() {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from);
            message.setTo(recipient);
            message.setSubject(CONFIRMATION_SUBJECT);

            String confirmationLink = baseUrl + AUTH_CONFIRMATION_ENDPOINT + PRINCIPAL_URI + token;
            message.setText(CONFIRMATION_CONTENT.apply(confirmationLink));

            mailSender.send(message);
        }

    }



    private class RecoveryEmailSenderDelegate {

        private static final String RECOVERY_SUBJECT = "Link for account recovery";

        private static final UnaryOperator<String> RECOVERY_CONTENT =
            link -> String.format(
                "Hello!\n\nYour recovery link is %s\n\nPlease, follow the link as soon as possible.", link
            );

        private final String recipient;

        private final String token;

        public RecoveryEmailSenderDelegate(String recipient, String token) {
            this.recipient = recipient;
            this.token = token;
        }

        void sendEmail() {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from);
            message.setTo(recipient);
            message.setSubject(RECOVERY_SUBJECT);

            String confirmationLink = baseUrl + AUTH_RECOVERY_ENDPOINT + PRINCIPAL_URI + token;
            message.setText(RECOVERY_CONTENT.apply(confirmationLink));

            mailSender.send(message);
        }

    }



    private class ChangeEmailSenderDelegate {

        private static final String CHANGE_EMAIL_SUBJECT = "Your email has been changed";

        private static final UnaryOperator<String> CHANGE_EMAIL_CONTENT =
            newAddress -> String.format(
                "Hello!\n\nYour account email-address has been changed to %s", newAddress
            );

        private final String recipient;

        private final String address;

        public ChangeEmailSenderDelegate(String recipient, String address) {
            this.recipient = recipient;
            this.address = address;
        }

        void sendEmail() {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from);
            message.setTo(recipient);
            message.setSubject(CHANGE_EMAIL_SUBJECT);

            message.setText(CHANGE_EMAIL_CONTENT.apply(address));

            mailSender.send(message);
        }

    }



    private class ChangePasswordSenderDelegate {

        private static final String CHANGE_PASSWORD_SUBJECT = "Your password has been changed";

        private static final String CHANGE_PASSWORD_CONTENT = "Hello!\n\nYour account email-address has been changed";

        private final String recipient;

        public ChangePasswordSenderDelegate(String recipient) {
            this.recipient = recipient;
        }

        void sendEmail() {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from);
            message.setTo(recipient);
            message.setSubject(CHANGE_PASSWORD_SUBJECT);

            message.setText(CHANGE_PASSWORD_CONTENT);

            mailSender.send(message);
        }

    }

}
