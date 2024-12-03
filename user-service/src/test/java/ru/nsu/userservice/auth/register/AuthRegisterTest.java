package ru.nsu.userservice.auth.register;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import ru.nsu.userservice.UserServiceTestConstants;
import ru.nsu.userservice.UserServiceTestInitializer;
import ru.nsu.userservice.auth.register.inapp.InAppRegisterRequestDTO;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthRegisterTest extends UserServiceTestInitializer {

    @Test
    void SuccessfulInAppRegister() throws Exception {
        String inAppRegisterRequestDTOString = objectMapper.writeValueAsString(
            new InAppRegisterRequestDTO(
                UserServiceTestConstants.VALID_EMAIL,
                UserServiceTestConstants.VALID_PASSWORD
            )
        );

        mockMvc.perform(
            put("/auth/register/in-app")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inAppRegisterRequestDTOString)
        ).andExpect(status().isOk());
    }

    private static Stream<Arguments> FailedInAppRegister() {
        return Stream.of(
            Arguments.of(
                new InAppRegisterRequestDTO(
                    null,
                    UserServiceTestConstants.VALID_PASSWORD
                )
            ),
            Arguments.of(
                new InAppRegisterRequestDTO(
                    "",
                    UserServiceTestConstants.VALID_PASSWORD
                )
            ),
            Arguments.of(
                new InAppRegisterRequestDTO(
                    "invalidEmail@",
                    UserServiceTestConstants.VALID_PASSWORD
                )
            ),
            Arguments.of(
                new InAppRegisterRequestDTO(
                    UserServiceTestConstants.VALID_EMAIL,
                    null
                )
            ),
            Arguments.of(
                new InAppRegisterRequestDTO(
                    UserServiceTestConstants.VALID_EMAIL,
                    ""
                )
            ),
            Arguments.of(
                new InAppRegisterRequestDTO(
                    UserServiceTestConstants.VALID_EMAIL,
                    "a".repeat(7)
                )
            ),
            Arguments.of(
                new InAppRegisterRequestDTO(
                    UserServiceTestConstants.VALID_EMAIL,
                    "a".repeat(65)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("FailedInAppRegister")
    void FailedInAppRegister(InAppRegisterRequestDTO inAppRegisterRequestDTO) throws Exception {
        String inAppRegisterRequestDTOString = objectMapper.writeValueAsString(inAppRegisterRequestDTO);

        mockMvc.perform(
            put("/auth/register/in-app")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inAppRegisterRequestDTOString)
        ).andExpect(status().isBadRequest());
    }

}
