package ua.knu.csc.emagazine.api.dto.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateCategoryDTO {

    @NotNull(message = "Category name must be not null")
    @NotBlank(message = "Category name must be not blank")
    @Size(min = 3, max = 15,
        message = "Category name must have length between {min} and {max}")
    private String value;

    public CreateCategoryDTO(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
