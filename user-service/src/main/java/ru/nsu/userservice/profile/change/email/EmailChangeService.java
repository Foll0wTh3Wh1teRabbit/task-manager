package ru.nsu.userservice.profile.change.email;

public interface EmailChangeService {

    EmailChangeResponseDTO change(String principal, EmailChangeRequestDTO changeDTO);

}
