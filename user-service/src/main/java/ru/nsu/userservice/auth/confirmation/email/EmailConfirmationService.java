package ru.nsu.userservice.auth.confirmation.email;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.nsu.common.constants.TokenTimeToLive;
import ru.nsu.common.model.User;
import ru.nsu.common.model.UserStatus;
import ru.nsu.common.repository.UserRepository;
import ru.nsu.common.service.JwtService;
import ru.nsu.userservice.auth.confirmation.AbstractConfirmationDTO;
import ru.nsu.userservice.auth.confirmation.ConfirmationResponseDTO;
import ru.nsu.userservice.auth.confirmation.ConfirmationService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailConfirmationService implements ConfirmationService {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    @Override
    public ConfirmationResponseDTO confirmation(AbstractConfirmationDTO confirmationDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user.setStatus(UserStatus.CONFIRMED);
        userRepository.save(user);

        String longTimeToLiveToken = jwtService.generateToken(user, TokenTimeToLive.LONG_TIME_TO_LIVE);

        return new ConfirmationResponseDTO(longTimeToLiveToken);
    }

}
