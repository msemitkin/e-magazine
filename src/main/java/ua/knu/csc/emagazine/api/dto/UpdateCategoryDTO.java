package ua.knu.csc.emagazine.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UpdateCategoryDTO {

    @NotNull
    private Integer id;
    @NotNull(message = "Category name must be not null")
    @NotBlank(message = "Category name must be not blank")
    @Size(min = 3, max = 15,
        message = "Category name must have length between {min} and {max}")
    private String value;

    public UpdateCategoryDTO(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCategoryDTO that = (UpdateCategoryDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    @Override
    public String toString() {
        return "UpdateCategoryDTO{" +
               "id=" + id +
               ", value='" + value + '\'' +
               '}';
    }
}
