package ua.nure.bekuzarov.SummaryTask4.entity;


import ua.nure.bekuzarov.SummaryTask4.annotation.Extract;
import ua.nure.bekuzarov.SummaryTask4.annotation.Prefix;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Publisher class.
 */
@Prefix("publisher")
@XmlRootElement
public class Publisher extends Entity {

    @Extract
    private Translation title;

    public Translation getTitle() {
        return title;
    }

    public void setTitle(Translation title) {
        this.title = title;
    }
}
