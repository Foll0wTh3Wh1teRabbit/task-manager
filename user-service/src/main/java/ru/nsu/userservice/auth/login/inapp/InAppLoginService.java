package ru.nsu.userservice.auth.login.inapp;

public interface InAppLoginService {

    InAppLoginResponseDTO login(InAppLoginRequestDTO loginDTO);

}
