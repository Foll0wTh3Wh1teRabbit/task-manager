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
import ru.nsu.userservice.auth.recovery.AbstractRecoveryDTO;
import ru.nsu.userservice.auth.recovery.RecoveryResponseDTO;
import ru.nsu.userservice.auth.recovery.RecoveryService;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecoveryInitializationService implements RecoveryService {

    private final JwtService jwtService;

    private final EmailService emailService;

    private final UserRepository userRepository;

    @Override
    public RecoveryResponseDTO recovery(AbstractRecoveryDTO recoveryDTO) {
        RecoveryInitializationDTO recoveryInitializationDTO = (RecoveryInitializationDTO) recoveryDTO;

        User user =  userRepository.findByEmail(recoveryInitializationDTO.getEmail())
            .filter(foundUser -> UserStatus.CONFIRMED.equals(foundUser.getStatus()))
            .orElseThrow(
                () -> new UsernameNotFoundException("User with email " + recoveryInitializationDTO.getEmail() + " is not found")
            );

        String shortTimeToLiveToken = jwtService.generateToken(user, TokenTimeToLive.SHORT_TIME_TO_LIVE);

        emailService.sendRecoveryEmail(recoveryInitializationDTO.getEmail(), shortTimeToLiveToken);

        return new RecoveryResponseDTO(recoveryInitializationDTO.getEmail());
    }

}
