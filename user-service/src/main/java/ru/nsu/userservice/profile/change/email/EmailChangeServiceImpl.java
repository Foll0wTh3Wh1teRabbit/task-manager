package ru.nsu.userservice.profile.change.email;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.common.model.User;
import ru.nsu.common.repository.UserRepository;
import ru.nsu.common.service.CustomUserDetailsService;
import ru.nsu.common.service.EmailService;
import ru.nsu.common.service.JwtService;

import static ru.nsu.common.constants.TokenTimeToLive.LONG_TIME_TO_LIVE;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailChangeServiceImpl implements EmailChangeService {

    private final JwtService jwtService;

    private final CustomUserDetailsService customUserDetailsService;

    private final UserRepository userRepository;

    private final EmailService emailService;

    @Override
    public EmailChangeResponseDTO changeEmail(String principal, EmailChangeRequestDTO changeDTO) {
        String username = jwtService.extractUsername(principal);
        User user = customUserDetailsService.loadUserByUsername(username);

        String oldEmail = user.getEmail();
        String newEmail = changeDTO.getNewEmail();

        user.setEmail(newEmail);
        userRepository.saveAndFlush(user);

        emailService.sendEmailChangedEmail(oldEmail, newEmail);
        emailService.sendEmailChangedEmail(newEmail, newEmail);

        String newLongTimeToLiveToken = jwtService.generateToken(user, LONG_TIME_TO_LIVE);

        return new EmailChangeResponseDTO(newLongTimeToLiveToken);
    }

}
