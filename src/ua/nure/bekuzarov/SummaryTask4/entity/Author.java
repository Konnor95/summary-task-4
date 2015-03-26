package ua.nure.bekuzarov.SummaryTask4.entity;

import ua.nure.bekuzarov.SummaryTask4.annotation.Column;
import ua.nure.bekuzarov.SummaryTask4.annotation.Extract;
import ua.nure.bekuzarov.SummaryTask4.annotation.Prefix;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Author class.
 *
 * @author Dmitry Bekuzarov
 */
@Prefix("author")
@XmlRootElement
public class Author extends Entity {

    @Extract
    private Translation name;

    @Extract
    private Translation description;

    @Column
    private String image;

    public Translation getName() {
        return name;
    }

    public void setName(Translation name) {
        this.name = name;
    }

    public Translation getDescription() {
        return description;
    }

    public void setDescription(Translation description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
