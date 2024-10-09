package ru.nsu.userservice.profile.change;

public interface ChangeService {

    ChangeResponseDTO change(String principal, AbstractChangeDTO changeDTO);

}
