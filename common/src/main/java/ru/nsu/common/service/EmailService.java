package ru.nsu.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.nsu.common.constants.Parameters;
import ru.nsu.common.constants.Path;

import java.util.function.UnaryOperator;

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

            String confirmationLink = baseUrl + Path.AUTH + Path.CONFIRMATION + Parameters.PRINCIPAL_URI + token;
            message.setText(CONFIRMATION_CONTENT.apply(confirmationLink));

            mailSender.send(message);
        }

    }

    private class RecoveryEmailSenderDelegate {

        private static final String RECOVERY_SUBJECT = "Link for account recovery";

        private static final UnaryOperator<String> CONFIRMATION_CONTENT =
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

            String confirmationLink = baseUrl + Path.AUTH + Path.RECOVERY + Parameters.PRINCIPAL_URI + token;
            message.setText(CONFIRMATION_CONTENT.apply(confirmationLink));

            mailSender.send(message);
        }

    }

}
