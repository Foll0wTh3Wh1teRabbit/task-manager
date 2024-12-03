package ru.nsu.userservice.auth.recovery.initialization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nsu.common.constants.TokenTimeToLive;
import ru.nsu.common.model.User;
import ru.nsu.common.model.UserStatus;
import ru.nsu.common.repository.UserRepository;
import ru.nsu.common.service.EmailService;
import ru.nsu.common.service.JwtService;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecoveryInitializationServiceImpl implements RecoveryInitializationService {

    private final JwtService jwtService;

    private final EmailService emailService;

    private final UserRepository userRepository;

    @Override
    public RecoveryInitializationResponseDTO recovery(RecoveryInitializationRequestDTO recoveryDTO) {
        User user =  userRepository.findByEmail(recoveryDTO.getEmail())
            .filter(foundUser -> UserStatus.CONFIRMED.equals(foundUser.getStatus()))
            .orElseThrow(
                () -> new UsernameNotFoundException("User with email " + recoveryDTO.getEmail() + " is not found")
            );

        String shortTimeToLiveToken = jwtService.generateToken(user, TokenTimeToLive.SHORT_TIME_TO_LIVE);

        emailService.sendRecoveryEmail(recoveryDTO.getEmail(), shortTimeToLiveToken);

        return new RecoveryInitializationResponseDTO(recoveryDTO.getEmail());
    }

}
