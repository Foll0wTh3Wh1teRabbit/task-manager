package ru.nsu.userservice.profile.change.password;

public interface PasswordChangeService {

    PasswordChangeResponseDTO changePassword(String principal, PasswordChangeRequestDTO changeDTO);

}
