package ua.knu.csc.emagazine.api.dto.magazine;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateMagazineDTO {

    @NotNull(message = "Name must be not null")
    @NotBlank(message = "Name must be not blank")
    private String name;
    @NotNull(message = "Category id must be not null")
    private Integer categoryId;

    public CreateMagazineDTO(String name, Integer categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
}
