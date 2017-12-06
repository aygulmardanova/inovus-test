package com.inovus.models;

//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by aygulmardanova on 01.12.2017.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_visit_time")
    private Timestamp lastVisitTime;

    @Column(name = "last_visit_ip")
    private String lastVisitIp;

    @Column(name = "new_visit_time")
    private Timestamp newVisitTime;

    @Column(name = "new_visit_ip")
    private String newVisitIp;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Timestamp lastVisitTime, String lastVisitIp) {
        this.username = username;
        this.password = password;
        this.lastVisitTime = lastVisitTime;
        this.lastVisitIp = lastVisitIp;
    }

    public User(String username, String password, Timestamp lastVisitTime, String lastVisitIp, Timestamp newVisitTime, String newVisitIp) {
        this.username = username;
        this.password = password;
        this.lastVisitTime = lastVisitTime;
        this.lastVisitIp = lastVisitIp;
        this.newVisitTime = newVisitTime;
        this.newVisitIp = newVisitIp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Timestamp lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public String getLastVisitIp() {
        return lastVisitIp;
    }

    public void setLastVisitIp(String lastVisitIp) {
        this.lastVisitIp = lastVisitIp;
    }

    public Timestamp getNewVisitTime() {
        return newVisitTime;
    }

    public void setNewVisitTime(Timestamp newVisitTime) {
        this.newVisitTime = newVisitTime;
    }

    public String getNewVisitIp() {
        return newVisitIp;
    }

    public void setNewVisitIp(String newVisitIp) {
        this.newVisitIp = newVisitIp;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
