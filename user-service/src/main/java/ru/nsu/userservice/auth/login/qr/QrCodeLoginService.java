package ru.nsu.userservice.auth.login.qr;

public interface QrCodeLoginService {

    QrCodeLoginResponseDTO login(QrCodeLoginRequestDTO loginDTO);

}
