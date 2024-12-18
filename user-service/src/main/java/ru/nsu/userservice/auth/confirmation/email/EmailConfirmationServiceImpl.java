package ru.nsu.userservice.auth.confirmation.email;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nsu.common.model.User;
import ru.nsu.common.repository.UserRepository;
import ru.nsu.common.service.JwtService;

import static ru.nsu.common.constants.TokenTimeToLive.*;
import static ru.nsu.common.model.User.UserStatus.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailConfirmationServiceImpl implements EmailConfirmationService {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    @Override
    public EmailConfirmationResponseDTO confirmation(EmailConfirmationRequestDTO confirmationDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user.setStatus(CONFIRMED);
        userRepository.saveAndFlush(user);

        String longTimeToLiveToken = jwtService.generateToken(user, LONG_TIME_TO_LIVE);

        return new EmailConfirmationResponseDTO(longTimeToLiveToken);
    }

}
