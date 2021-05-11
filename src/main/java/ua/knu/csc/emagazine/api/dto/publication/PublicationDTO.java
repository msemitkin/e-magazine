package ua.knu.csc.emagazine.api.dto.publication;

import java.util.List;

public class PublicationDTO {
    private final Integer id;
    private final String name;
    private final String text;
    private final List<String> keyWords;

    public PublicationDTO(Integer id, String name, String text, List<String> keyWords) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.keyWords = keyWords;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }
}
