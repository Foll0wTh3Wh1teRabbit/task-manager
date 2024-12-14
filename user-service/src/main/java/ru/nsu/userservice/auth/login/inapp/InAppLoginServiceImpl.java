package ru.nsu.userservice.auth.login.inapp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.common.service.JwtService;
import ru.nsu.common.repository.UserRepository;

import static ru.nsu.common.constants.TokenTimeToLive.*;
import static ru.nsu.common.model.User.UserStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class InAppLoginServiceImpl implements InAppLoginService {

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public InAppLoginResponseDTO login(InAppLoginRequestDTO inAppLoginRequestDTO) {
        UserDetails userDetails = userRepository.findByEmail(inAppLoginRequestDTO.getEmail())
            .filter(user -> CONFIRMED.equals(user.getStatus()))
            .orElseThrow(
                () -> new UsernameNotFoundException("User with email " + inAppLoginRequestDTO.getEmail() + " is not found")
            );

        if (!passwordEncoder.matches(inAppLoginRequestDTO.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        String longTimeToLiveToken = jwtService.generateToken(userDetails, LONG_TIME_TO_LIVE);

        return new InAppLoginResponseDTO(longTimeToLiveToken);
    }

}
