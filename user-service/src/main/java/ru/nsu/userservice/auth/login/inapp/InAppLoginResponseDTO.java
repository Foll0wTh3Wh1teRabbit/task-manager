package ru.nsu.userservice.auth.login.inapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InAppLoginResponseDTO {

    private String principal;

}
