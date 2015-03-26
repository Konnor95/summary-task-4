package ua.nure.bekuzarov.SummaryTask4.entity;

import ua.nure.bekuzarov.SummaryTask4.annotation.Column;
import ua.nure.bekuzarov.SummaryTask4.annotation.Extract;
import ua.nure.bekuzarov.SummaryTask4.annotation.Prefix;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Book class.
 *
 * @author Dmitry Bekuzarov
 */
@Prefix("book")
@XmlRootElement
public class Book extends BookInfo {

    @Column
    private Integer year;

    @Column
    private Integer pages;

    @Column
    private String image;

    @Extract
    private Translation title;

    @Extract
    private Translation description;

    @Extract("publisher")
    private Publisher publisher;

    @Extract
    private List<Author> authors;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Translation getTitle() {
        return title;
    }

    public void setTitle(Translation title) {
        this.title = title;
    }

    public Translation getDescription() {
        return description;
    }

    public void setDescription(Translation description) {
        this.description = description;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @XmlElementWrapper
    @XmlElement(name = "author")
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
