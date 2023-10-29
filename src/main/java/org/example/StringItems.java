package org.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "StringItems")
public class StringItems {
    private List<StringItem> items;

    public StringItems() {
    }

    public StringItems(List<StringItem> items) {
        this.items = items;
    }

    @XmlElement(name = "StringItem")
    public List<StringItem> getItems() {
        return items;
    }

    public void setItems(List<StringItem> items) {
        this.items = items;
    }
}
