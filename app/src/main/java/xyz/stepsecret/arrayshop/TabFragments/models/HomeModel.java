package xyz.stepsecret.arrayshop.TabFragments.models;

/**
 * Created by Lincoln on 15/01/16.
 */
public class HomeModel {
    private String id_queue, name, date, number_book, queue , table;

    public HomeModel() {
    }

    public HomeModel(String id_queue, String name, String date, String number_book, String queue, String table) {
        this.id_queue = id_queue;
        this.name = name;
        this.date = date;
        this.number_book = number_book;
        this.queue = queue;
        this.table = table;

    }

    public String getIdqueue() {
        return id_queue;
    }

    public void setIdqueue(String id_queue) {
        this.id_queue = id_queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumberbook() {
        return number_book;
    }

    public void setNumberbook(String number_book) {
        this.number_book = number_book;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }


}
