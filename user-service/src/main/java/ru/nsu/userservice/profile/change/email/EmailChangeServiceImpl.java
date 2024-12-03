package ru.nsu.userservice.profile.change.email;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nsu.common.constants.TokenTimeToLive;
import ru.nsu.common.model.User;
import ru.nsu.common.repository.UserRepository;
import ru.nsu.common.service.JwtService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailChangeServiceImpl implements EmailChangeService {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    @Override
    public EmailChangeResponseDTO change(String principal, EmailChangeRequestDTO changeDTO) {
        String username = jwtService.extractUsername(principal);
        User user = userRepository.findByEmail(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User with email " + username + " is not found")
            );

        user.setEmail(changeDTO.getNewEmail());
        userRepository.save(user);

        String newLongTimeToLiveToken = jwtService.generateToken(user, TokenTimeToLive.LONG_TIME_TO_LIVE);

        return new EmailChangeResponseDTO(newLongTimeToLiveToken);
    }

}
