package org.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name="Names")
public class NameItems {

    private List<NameItem> items;


    public NameItems(){}

    public NameItems(List<NameItem> itemList){
        this.items = itemList;
    }

    @XmlElement(name="Name")
    public List<NameItem> getItems(){return items;}


}

class NameItem{

    private String firstName;
    private String lastName;

    @XmlElement(name="FirstName")
    public String getFistName(){

        return firstName;
    }


    @XmlElement(name="LastName")
    public String getLastName(){
        return lastName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
       this.lastName = lastName;
    }

}