package com.example.ls.shoppingmall.home.home_db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by ls on 2017/11/13.
 */
@Table(name = "HistoryDb")
public class HistoryDb {
    //主键
    @Column(name = "id", isId = true)
    public int id;
    @Column(name = "name")
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HistoryDb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

