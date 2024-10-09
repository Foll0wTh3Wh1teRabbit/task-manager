package ru.nsu.userservice.auth.login.qr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nsu.common.constants.TokenTimeToLive;
import ru.nsu.common.model.User;
import ru.nsu.common.service.JwtService;
import ru.nsu.userservice.auth.login.AbstractLoginDTO;
import ru.nsu.userservice.auth.login.LoginResponseDTO;
import ru.nsu.userservice.auth.login.LoginService;

@Slf4j
@Service
@RequiredArgsConstructor
public class QrCodeLoginService implements LoginService {

    private final JwtService jwtService;

    @Override
    public LoginResponseDTO login(AbstractLoginDTO loginDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String longTimeToLiveToken = jwtService.generateToken(user, TokenTimeToLive.LONG_TIME_TO_LIVE);

        return new LoginResponseDTO(longTimeToLiveToken);
    }

}
