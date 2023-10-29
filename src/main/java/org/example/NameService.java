package org.example;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class NameService {

    private static NameService instance;

    private boolean init = false;

    private CopyOnWriteArrayList<NameItem> list;

    public static NameService getInstance() {
        if (NameService.instance == null) {
            NameService.instance = new NameService();
        }

        return NameService.instance;
    }

    private NameService() {
    }

    public void populate() {
        if (init == true) return;

        init = true;

        this.list = new CopyOnWriteArrayList<>();

        NameItem first = new NameItem();

        first.setFirstName("Simon");

        first.setLastName("Leroux");

        this.list.add(first);
    }


    public List<NameItem> getItems() {
        return new ArrayList<>(this.list);
    }


    public boolean add(String firstName, String lastName) {


        for (NameItem i : this.list) {
            if (
                i.getFistName().equals(firstName)
                &&
                i.getLastName().equals(lastName)
            ) {
                return false;
            }
        }

        NameItem newItem = new NameItem();
        newItem.setFirstName(firstName);
        newItem.setLastName(lastName);

        this.list.add(newItem);

        return true;
    }


    public boolean remove(String firstName, String lastName) {

        for (NameItem i : this.list) {

            if (
                    i.getFistName().equals(firstName)
                            &&
                            i.getLastName().equals(lastName)
            ) {

                this.list.remove(i);

                return true;
            }
        }

        return false;
    }
}
