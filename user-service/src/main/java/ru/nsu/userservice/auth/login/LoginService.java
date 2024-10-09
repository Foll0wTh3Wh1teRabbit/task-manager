package ru.nsu.userservice.auth.login;

public interface LoginService {

    LoginResponseDTO login(AbstractLoginDTO loginDTO);

}
