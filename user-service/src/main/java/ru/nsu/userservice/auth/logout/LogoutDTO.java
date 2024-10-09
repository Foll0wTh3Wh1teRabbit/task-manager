package ru.nsu.userservice.auth.logout;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogoutDTO {

    private String principal;

}
