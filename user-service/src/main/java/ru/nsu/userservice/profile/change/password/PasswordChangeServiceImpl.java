package ru.nsu.userservice.profile.change.password;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class PasswordChangeServiceImpl implements PasswordChangeService {

    private final JwtService jwtService;

    private final CustomUserDetailsService customUserDetailsService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Override
    public PasswordChangeResponseDTO changePassword(String principal, PasswordChangeRequestDTO changeDTO) {
        String username = jwtService.extractUsername(principal);
        User user = customUserDetailsService.loadUserByUsername(username);

        boolean oldPasswordIsCorrect = passwordEncoder.matches(changeDTO.getOldPassword(), user.getPassword());
        if (!oldPasswordIsCorrect) {
            throw new BadCredentialsException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(changeDTO.getNewPassword()));
        userRepository.saveAndFlush(user);

        emailService.sendPasswordChangedEmail(user.getEmail());

        String newLongTimeToLiveToken = jwtService.generateToken(user, LONG_TIME_TO_LIVE);

        return new PasswordChangeResponseDTO(newLongTimeToLiveToken);
    }

}
