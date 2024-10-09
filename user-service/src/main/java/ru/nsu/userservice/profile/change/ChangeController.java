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
import ru.nsu.userservice.profile.change.email.EmailChangeDTO;
import ru.nsu.userservice.profile.change.password.PasswordChangeDTO;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ChangeController {

    private final ChangeService emailChangeService;

    private final ChangeService passwordChangeService;

    @PatchMapping(Path.PROFILE + Path.CHANGE + Path.EMAIL)
    public ChangeResponseDTO changeEmail(
        @RequestHeader("Authorization") String principal,
        @RequestBody @Valid EmailChangeDTO emailChangeDTO
    ) {
        log.info("change <- type: EMAIL, dto: {}", emailChangeDTO);

        return emailChangeService.change(principal, emailChangeDTO);
    }

    @PatchMapping(Path.PROFILE + Path.CHANGE + Path.PASSWORD)
    public ChangeResponseDTO changePassword(
        @RequestHeader("Authorization") String principal,
        @RequestBody @Valid PasswordChangeDTO passwordChangeDTO
    ) {
        log.info("change <- type: PASSWORD");

        return passwordChangeService.change(principal, passwordChangeDTO);
    }

}
