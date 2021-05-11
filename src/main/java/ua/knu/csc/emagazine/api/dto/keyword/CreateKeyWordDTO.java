package ua.knu.csc.emagazine.api.dto.keyword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateKeyWordDTO {

    @NotNull(message = "Key word must be not null")
    @NotBlank(message = "Key word must be not blank")
    private String value;

    public CreateKeyWordDTO(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
