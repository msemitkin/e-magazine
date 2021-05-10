package ua.knu.csc.emagazine.api.dto.publisher;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreatePublisherDTO {
    @NotNull(message = "Name must be not null")
    @NotBlank(message = "Name must be not blank")
    private String name;

    public CreatePublisherDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
