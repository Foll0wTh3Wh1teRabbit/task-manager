package ru.nsu.userservice.auth.register.inapp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.common.model.User;
import ru.nsu.common.repository.UserRepository;
import ru.nsu.common.service.EmailService;
import ru.nsu.common.service.JwtService;

import static ru.nsu.common.constants.TokenTimeToLive.SHORT_TIME_TO_LIVE;
import static ru.nsu.common.model.User.UserStatus.NOT_CONFIRMED;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class InAppRegisterServiceImpl implements InAppRegisterService {

    private final EmailService emailService;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public InAppRegisterResponseDTO register(InAppRegisterRequestDTO registerDTO) {
        User user = createUser(registerDTO);
        userRepository.saveAndFlush(user);

        String shortTimeToLiveToken = jwtService.generateToken(user, SHORT_TIME_TO_LIVE);
        emailService.sendConfirmationEmail(user.getEmail(), shortTimeToLiveToken);

        return new InAppRegisterResponseDTO(registerDTO.getEmail());
    }

    private User createUser(InAppRegisterRequestDTO inAppRegisterRequestDTO) {
        User user = new User();
        user.setEmail(inAppRegisterRequestDTO.getEmail());
        user.setPassword(
            passwordEncoder.encode(
                inAppRegisterRequestDTO.getPassword()
            )
        );
        user.setStatus(NOT_CONFIRMED);

        return user;
    }

}
