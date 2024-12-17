package ru.nsu.userservice.auth.recovery.confirmation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.common.model.User;
import ru.nsu.common.repository.UserRepository;
import ru.nsu.common.service.JwtService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecoveryConfirmationServiceImpl implements RecoveryConfirmationService {

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public RecoveryConfirmationResponseDTO recovery(RecoveryConfirmationRequestDTO recoveryDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();

        user.setPassword(passwordEncoder.encode(recoveryDTO.getNewPassword()));
        userRepository.save(user);

        return new RecoveryConfirmationResponseDTO(username);
    }

}
