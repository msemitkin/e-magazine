package ua.knu.csc.emagazine.domain.publication;

import ua.knu.csc.emagazine.domain.keyword.KeyWord;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "publication")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "text")
    private String text;
    @ManyToMany
    private List<KeyWord> keyWords;
    @Column(nullable = false)
    private LocalDateTime publicationDateTime;

    protected Publication() {
    }

    public Publication(
        Integer id,
        String name,
        String text,
        List<KeyWord> keyWords,
        LocalDateTime publicationDateTime
    ) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.keyWords = keyWords;
        this.publicationDateTime = publicationDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<KeyWord> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<KeyWord> keyWords) {
        this.keyWords = keyWords;
    }

    public LocalDateTime getPublicationDateTime() {
        return publicationDateTime;
    }

    public void setPublicationDateTime(LocalDateTime publicationDateTime) {
        this.publicationDateTime = publicationDateTime;
    }
}
