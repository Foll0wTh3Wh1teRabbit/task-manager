package ru.nsu.userservice.auth.login.qr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nsu.common.model.User;
import ru.nsu.common.service.JwtService;

import static ru.nsu.common.constants.TokenTimeToLive.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class QrCodeLoginServiceImpl implements QrCodeLoginService {

    private final JwtService jwtService;

    @Override
    public QrCodeLoginResponseDTO login(QrCodeLoginRequestDTO loginDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String longTimeToLiveToken = jwtService.generateToken(user, LONG_TIME_TO_LIVE);

        return new QrCodeLoginResponseDTO(longTimeToLiveToken);
    }

}
