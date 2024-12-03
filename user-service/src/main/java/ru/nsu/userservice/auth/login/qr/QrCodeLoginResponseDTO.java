package ru.nsu.userservice.auth.login.qr;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class QrCodeLoginResponseDTO {

    private String principal;

}
