package ru.nsu.userservice.profile.change.password;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.common.constants.TokenTimeToLive;
import ru.nsu.common.model.User;
import ru.nsu.common.repository.UserRepository;
import ru.nsu.common.service.JwtService;
import ru.nsu.userservice.profile.change.AbstractChangeDTO;
import ru.nsu.userservice.profile.change.ChangeResponseDTO;
import ru.nsu.userservice.profile.change.ChangeService;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordChangeService implements ChangeService {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ChangeResponseDTO change(String principal, AbstractChangeDTO changeDTO) {
        PasswordChangeDTO passwordChangeDTO = (PasswordChangeDTO) changeDTO;

        String username = jwtService.extractUsername(principal);
        User user = userRepository.findByEmail(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User with email " + username + " is not found")
            );

        boolean oldPasswordIsCorrect = passwordEncoder.matches(passwordChangeDTO.getOldPassword(), user.getPassword());
        if (!oldPasswordIsCorrect) {
            throw new BadCredentialsException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
        userRepository.save(user);

        String newLongTimeToLiveToken = jwtService.generateToken(user, TokenTimeToLive.LONG_TIME_TO_LIVE);

        return new ChangeResponseDTO(newLongTimeToLiveToken);
    }

}
