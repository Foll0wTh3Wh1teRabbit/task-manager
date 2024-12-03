package ru.nsu.userservice.auth.login;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import ru.nsu.userservice.UserServiceTestConstants;
import ru.nsu.userservice.UserServiceTestInitializer;
import ru.nsu.userservice.auth.login.inapp.InAppLoginRequestDTO;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthLoginTest extends UserServiceTestInitializer {

    @BeforeAll
    public static void beforeAll() {

    }

    @Test
    public void SuccessfulInAppLogin() {

    }

    @Test
    public void SuccessfulQrCodeLogin() {

    }

    private static Stream<Arguments> FailedInAppLogin_Parameters() {
        return Stream.of(
            Arguments.of(
                new InAppLoginRequestDTO(
                    null,
                    UserServiceTestConstants.VALID_PASSWORD
                )
            ),
            Arguments.of(
                new InAppLoginRequestDTO(
                    "",
                    UserServiceTestConstants.VALID_PASSWORD
                )
            ),
            Arguments.of(
                new InAppLoginRequestDTO(
                    "invalidEmail@",
                    UserServiceTestConstants.VALID_PASSWORD
                )
            ),
            Arguments.of(
                new InAppLoginRequestDTO(
                    UserServiceTestConstants.VALID_EMAIL,
                    null
                )
            ),
            Arguments.of(
                new InAppLoginRequestDTO(
                    UserServiceTestConstants.VALID_EMAIL,
                    ""
                )
            ),
            Arguments.of(
                new InAppLoginRequestDTO(
                    UserServiceTestConstants.VALID_EMAIL,
                    "a".repeat(7)
                )
            ),
            Arguments.of(
                new InAppLoginRequestDTO(
                    UserServiceTestConstants.VALID_EMAIL,
                    "a".repeat(65)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("FailedInAppLogin_Parameters")
    void FailedInAppLogin(InAppLoginRequestDTO inAppLoginRequestDTO) throws Exception {
        String inAppLoginRequestDTOString = objectMapper.writeValueAsString(inAppLoginRequestDTO);

        mockMvc.perform(
            put("/auth/register/in-app")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inAppLoginRequestDTOString)
        ).andExpect(status().isBadRequest());
    }

}
