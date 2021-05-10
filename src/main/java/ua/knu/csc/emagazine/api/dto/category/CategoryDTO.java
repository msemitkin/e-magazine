package ua.knu.csc.emagazine.api.dto.category;

import java.util.Objects;

public class CategoryDTO {

    private Integer id;
    private String  value;

    public CategoryDTO(Integer id, String value) {
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
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
               "id=" + id +
               ", value='" + value + '\'' +
               '}';
    }
}
