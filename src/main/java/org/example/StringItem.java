
package org.example;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StringItem {
    private String value;

    public StringItem() {
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }


}
