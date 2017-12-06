package com.inovus.models;

import javax.persistence.*;

@Entity
@Table(name = "pages_statistics")
public class PagesStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pages_statistics_id_seq")
    @SequenceGenerator(name = "pages_statistics_id_seq", sequenceName = "pages_statistics_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "page")
    private String page;

    @Column(name = "count")
    private int count;

    public PagesStatistic() {
    }

    public PagesStatistic(String page, int count) {
        this.page = page;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PagesStatistic{" +
                "page='" + page + '\'' +
                ", count=" + count +
                '}';
    }
}
