package ru.nsu.userservice.auth.register;

public interface RegisterService {

    RegisterResponseDTO register(AbstractRegisterDTO registerDTO);

}
