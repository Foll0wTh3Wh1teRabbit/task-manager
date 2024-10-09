package ru.nsu.userservice.auth.login.inapp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.common.constants.TokenTimeToLive;
import ru.nsu.common.model.UserStatus;
import ru.nsu.common.service.JwtService;
import ru.nsu.userservice.auth.login.AbstractLoginDTO;
import ru.nsu.userservice.auth.login.LoginResponseDTO;
import ru.nsu.userservice.auth.login.LoginService;
import ru.nsu.common.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class InAppLoginService implements LoginService {

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public LoginResponseDTO login(AbstractLoginDTO loginDTO) {
        InAppLoginDTO inAppLoginDTO = (InAppLoginDTO) loginDTO;

        UserDetails userDetails = userRepository.findByEmail(inAppLoginDTO.getEmail())
            .filter(user -> UserStatus.CONFIRMED.equals(user.getStatus()))
            .orElseThrow(
                () -> new UsernameNotFoundException("User with email " + inAppLoginDTO.getEmail() + " is not found")
            );

        if (!passwordEncoder.matches(inAppLoginDTO.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        String longTimeToLiveToken = jwtService.generateToken(userDetails, TokenTimeToLive.LONG_TIME_TO_LIVE);

        return new LoginResponseDTO(longTimeToLiveToken);
    }

}
