package entity;

import java.util.Date;

/**
 * Created by cantian on 2018/1/18.
 */

public class Item {
    public String title;
    public Date date;
    public int id;

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", id=" + id +
                '}';
    }

    public Item(String title, Date date, int id) {
        this.title = title;
        this.date = date;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
