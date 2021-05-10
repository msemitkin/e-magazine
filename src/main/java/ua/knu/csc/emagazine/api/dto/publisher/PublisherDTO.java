package ua.knu.csc.emagazine.api.dto.publisher;

public class PublisherDTO {
    private Integer id;
    private String name;

    public PublisherDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
