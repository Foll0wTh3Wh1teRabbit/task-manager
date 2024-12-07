package ru.nsu.userservice.profile.change;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.common.constants.Path;
import ru.nsu.userservice.profile.change.email.EmailChangeRequestDTO;
import ru.nsu.userservice.profile.change.email.EmailChangeResponseDTO;
import ru.nsu.userservice.profile.change.email.EmailChangeService;
import ru.nsu.userservice.profile.change.password.PasswordChangeRequestDTO;
import ru.nsu.userservice.profile.change.password.PasswordChangeResponseDTO;
import ru.nsu.userservice.profile.change.password.PasswordChangeService;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ChangeController {

    private final EmailChangeService emailChangeService;

    private final PasswordChangeService passwordChangeService;

    @PatchMapping(Path.PROFILE + Path.CHANGE + Path.EMAIL)
    public EmailChangeResponseDTO changeEmail(
        @RequestHeader("Authorization") String principal,
        @RequestBody @Valid EmailChangeRequestDTO emailChangeRequestDTO
    ) {
        log.info("change <- type: EMAIL, dto: {}", emailChangeRequestDTO);

        return emailChangeService.change(principal, emailChangeRequestDTO);
    }

    @PatchMapping(Path.PROFILE + Path.CHANGE + Path.PASSWORD)
    public PasswordChangeResponseDTO changePassword(
        @RequestHeader("Authorization") String principal,
        @RequestBody @Valid PasswordChangeRequestDTO passwordChangeRequestDTO
    ) {
        log.info("change <- type: PASSWORD");

        return passwordChangeService.change(principal, passwordChangeRequestDTO);
    }

}
