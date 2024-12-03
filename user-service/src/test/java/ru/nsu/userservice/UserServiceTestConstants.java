package ru.nsu.userservice;

import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

@UtilityClass
@FieldDefaults(makeFinal = true)
public class UserServiceTestConstants {

    public String VALID_EMAIL = "nkosarev4392@gmail.com";

    public String VALID_PASSWORD = "password";

    public String EMPTY_EMAIL_ERROR_MESSAGE = "Email-адрес не может быть пустым";

    public String INVALID_EMAIL_ERROR_MESSAGE = "Email-адрес должен быть задан в валидном формате";

    public String EMPTY_PASSWORD_ERROR_MESSAGE = "Пароль не может быть пустым";

    public String INSUFFICIENT_LENGTH_PASSWORD_ERROR_MESSAGE = "Пароль должен быть от 8 до 64 символов";

}
