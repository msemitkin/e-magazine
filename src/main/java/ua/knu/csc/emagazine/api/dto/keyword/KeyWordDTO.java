package ua.knu.csc.emagazine.api.dto.keyword;

public class KeyWordDTO {

    private final Integer id;
    private final String value;

    public KeyWordDTO(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
