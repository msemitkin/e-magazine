package ua.knu.csc.emagazine.domain.magazine;

import ua.knu.csc.emagazine.domain.Publication;
import ua.knu.csc.emagazine.domain.category.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "magazine")
public class Magazine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //    @Column(nullable = false)
    @Column(name = "name")
    private String name;

    @ManyToOne
    private Category category;
    @OneToMany
    @JoinColumn(name = "magazine_id")
    private List<Publication> publications;

    protected Magazine() {
    }

    public Magazine(Integer id, String name, Category category,
                    List<Publication> publications) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.publications = publications;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
