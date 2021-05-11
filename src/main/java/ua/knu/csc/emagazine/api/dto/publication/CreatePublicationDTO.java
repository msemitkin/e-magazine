package ua.knu.csc.emagazine.api.dto.publication;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CreatePublicationDTO {

    @NotNull(message = "Name must be not null")
    @NotBlank(message = "Name must be not blank")
    private String name;
    @NotNull(message = "Text must be not null")
    @NotBlank(message = "Text must be not blank")
    @Size(min = 100, message = "Article length must be at least {min} symbols")
    private String text;
    @NotNull
    @Size(min = 1, message = "Article must have at least one keyword")
    private List<Integer> keyWords;

    public CreatePublicationDTO(String name, String text, List<Integer> keyWords) {
        this.name = name;
        this.text = text;
        this.keyWords = keyWords;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public List<Integer> getKeyWords() {
        return keyWords;
    }
}
