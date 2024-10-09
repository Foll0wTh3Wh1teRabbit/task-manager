package ru.nsu.userservice.auth.recovery.confirmation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.common.model.User;
import ru.nsu.common.repository.UserRepository;
import ru.nsu.common.service.JwtService;
import ru.nsu.userservice.auth.recovery.AbstractRecoveryDTO;
import ru.nsu.userservice.auth.recovery.RecoveryResponseDTO;
import ru.nsu.userservice.auth.recovery.RecoveryService;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecoveryConfirmationService implements RecoveryService {

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public RecoveryResponseDTO recovery(AbstractRecoveryDTO recoveryDTO) {
        RecoveryConfirmationDTO recoveryConfirmationDTO = (RecoveryConfirmationDTO) recoveryDTO;

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();

        user.setPassword(passwordEncoder.encode(recoveryConfirmationDTO.getNewPassword()));
        userRepository.save(user);

        return new RecoveryResponseDTO(username);
    }

}
