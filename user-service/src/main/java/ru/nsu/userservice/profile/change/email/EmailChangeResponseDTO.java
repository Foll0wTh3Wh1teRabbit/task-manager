package ru.nsu.userservice.profile.change.email;

import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(makeFinal = true)
public class EmailChangeResponseDTO {

    private String principal;

}
