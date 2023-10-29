package org.example;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


public class StringService {

    private static StringService instance;

    private boolean init = false;

    private CopyOnWriteArrayList<StringItem> list;

    private StringService() {
    }

    public void populate() {

        if (init == true) return;

        init = true;

        this.list = new CopyOnWriteArrayList<>();

        StringItem first = new StringItem();
        first.setValue("The first");

        StringItem second = new StringItem();
        second.setValue("The second");


        this.list.add(first);
        this.list.add(second);

    }

    public static StringService getInstance() {

        if (StringService.instance == null) {
            StringService.instance = new StringService();

        }

        return StringService.instance;


    }


    public ArrayList<StringItem> getAll() {

        ArrayList<StringItem> result = new ArrayList<>(this.list);


        return result;
    }


    public boolean add(String v) {
        StringItem item = new StringItem();


        for(StringItem i : this.list){

            if (i.getValue().equals(v)) return false;
        }

        item.setValue(v);


        this.list.add(item);

        return true;

    }

    public boolean remove(String v) {


        for (StringItem i : this.list) {
            if (i.getValue().equals(v)) {
                this.list.remove(i) ;

                return true;

            }
        }

        return false;
    }

}
