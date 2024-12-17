package ru.nsu.userservice.auth.login.qr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrCodeLoginResponseDTO {

    private String principal;

}
