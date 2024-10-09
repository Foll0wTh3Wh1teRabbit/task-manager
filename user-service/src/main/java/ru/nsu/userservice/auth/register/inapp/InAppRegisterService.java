package ru.nsu.userservice.auth.register.inapp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.common.constants.TokenTimeToLive;
import ru.nsu.common.service.EmailService;
import ru.nsu.common.service.JwtService;
import ru.nsu.userservice.auth.register.RegisterService;
import ru.nsu.userservice.auth.register.AbstractRegisterDTO;
import ru.nsu.userservice.auth.register.RegisterResponseDTO;
import ru.nsu.common.model.User;
import ru.nsu.common.model.UserStatus;
import ru.nsu.common.repository.UserRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InAppRegisterService implements RegisterService {

    private final EmailService emailService;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public RegisterResponseDTO register(AbstractRegisterDTO registerDTO) {
        InAppRegisterDTO inAppRegisterDTO = (InAppRegisterDTO) registerDTO;

        User user = createUser(inAppRegisterDTO);
        userRepository.save(user);

        String shortTimeToLiveToken = jwtService.generateToken(user, TokenTimeToLive.SHORT_TIME_TO_LIVE);
        emailService.sendConfirmationEmail(user.getEmail(), shortTimeToLiveToken);

        return new RegisterResponseDTO(inAppRegisterDTO.getEmail());
    }

    private User createUser(InAppRegisterDTO inAppRegisterDTO) {
        User user = new User();
        user.setEmail(inAppRegisterDTO.getEmail());
        user.setPassword(
            passwordEncoder.encode(
                inAppRegisterDTO.getPassword()
            )
        );
        user.setStatus(UserStatus.NOT_CONFIRMED);

        return user;
    }

}
