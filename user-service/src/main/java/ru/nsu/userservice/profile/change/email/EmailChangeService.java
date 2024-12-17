package ru.nsu.userservice.profile.change.email;

public interface EmailChangeService {

    EmailChangeResponseDTO changeEmail(String principal, EmailChangeRequestDTO changeDTO);

}
