package ru.nsu.userservice.profile.change.password;

public interface PasswordChangeService {

    PasswordChangeResponseDTO change(String principal, PasswordChangeRequestDTO changeDTO);

}
